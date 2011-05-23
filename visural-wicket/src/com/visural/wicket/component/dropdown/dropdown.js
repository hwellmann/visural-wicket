/*
 *  Copyright 2009 Richard Nichols.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 *
 *  $Id: dropdown.js 256 2011-02-05 12:06:02Z tibes80@gmail.com $
 *
 */

var visural_dropdowns = [];
var visural_datasources = [];

function reverseMap(mapArray,makeUpper) {
    var revMap = [];
    for (var mv in mapArray) {
        if (makeUpper) {
            revMap[mapArray[mv].toString().toUpperCase()] = mv;
        } else {
            revMap[mapArray[mv]] = mv;
        }
    }
    return revMap;
}

function VisuralDataSource(p_dataSourceName, p_values) {
    this.dataSourceName = p_dataSourceName;
    this.values = p_values;
    this.revMap = reverseMap(p_values, true);

    this.filteredValues = function(filterText) {
        // short cut for no filter
        if (!filterText) {
            return this.values;
        }
        // real filter
        var filteredI = 0;
        var filtered = [];
        var filteredSWI = 0;
        var filteredSW = [];
        for (var entry in this.values) {
            var offset = this.values[entry].value.toUpperCase().indexOf(filterText.toString().toUpperCase(), 0);
            if (offset > -1) {
                this.values[entry].lastMatchOffset = offset;
                this.values[entry].lastMatchLength = filterText.length;
                if (offset == 0) {
                    filteredSW[filteredSWI] = this.values[entry];
                    filteredSWI++;
                }else {
                    filtered[filteredI] = this.values[entry];
                    filteredI++;
                }
            }
        }
        return filteredSW.concat(filtered);
    }

    this.indexForId = function(dsvId) {
       for (var fbiIdx in this.values) {
            if (this.values[fbiIdx].id == dsvId) {
                return fbiIdx;
            }
        }
        return -1;
    }

    this.indexForValue = function(dsvVal) {
        if (this.revMap[dsvVal.toString().toUpperCase()]) {
            return this.revMap[dsvVal.toString().toUpperCase()];
        } else {
            return -1;
        }
//        for (fbiIdx in this.values) {
//            if (this.values[fbiIdx].value.toUpperCase() == dsvVal.toString().toUpperCase()) {
//                return fbiIdx;
//            }
//        }
//        return -1;
    }
}

function VisuralDataSourceValue(p_id, p_value) {
    this.id = p_id;
    this.value = p_value;
    this.lastMatchOffset = 0;
    this.lastMatchLength = 0;
}

function VisuralDropDown(p_controlId, p_dataSourceName, p_allowAnyValue, p_enableFiltering, p_enableFilterToggle, p_onChangeCallback) {
    this.controlId = p_controlId;
    this.dataSourceName = p_dataSourceName;
    this.isOpen = false;
    this.inOpen = false;
    this.inClose = false;
    this.isFiltered = p_enableFiltering;
    this.allowAnyValue = p_allowAnyValue;
    this.keyedText = '';
    this.currentValues = [];
    this.keyScrollIdx = -1;
    this.dropDownDiv = jQuery('#visural_dropdown_'+this.controlId);
    this.idControl = jQuery('#visural_dropdown_id_'+this.controlId);
    this.valueControl = jQuery('#visural_dropdown_value_'+this.controlId);
    this.closeOnBlur = true;
    this.lastInputTime = 0;
    this.enableFiltering = p_enableFiltering;
    this.enableFilterToggle = p_enableFilterToggle;
    this.onChangeCallback = p_onChangeCallback;

    this.toggleFilter = function() {
        this.isFiltered = !this.isFiltered;
        this.refreshValues();
    }

    this.refreshValues = function() {
        this.currentValues = visural_datasources[this.dataSourceName].values;
        if (this.isFiltered && this.keyedText) {
            this.currentValues = visural_datasources[this.dataSourceName].filteredValues(this.keyedText); // not sel value.. want to scroll thru on keys
        }
        var ddcbuf = new VisuralStringBuffer();
        if (this.enableFiltering && this.enableFilterToggle) {
            ddcbuf.append('<a href="javascript:visural_togglefilter_dropdown(\''+this.controlId+'\')"> - ');
            if (this.isFiltered) {
                 ddcbuf.append('Show All');
            } else {
                 ddcbuf.append('Filter List');
            }
             ddcbuf.append(' -</a>');
        }
        var rowNum = 0;
        for (var entry in this.currentValues) {
            var rowClass = "none";
            if (this.currentValues[entry].id == this.selectedId()) {
                rowClass = "selected";
            }
            ddcbuf.append('<a id="');
            ddcbuf.append(this.controlId);
            ddcbuf.append(this.currentValues[entry].id)
            ddcbuf.append('"  class=\'');
            ddcbuf.append(rowClass);
            ddcbuf.append('\' href="javascript:visural_select_dropdown(\'');
            ddcbuf.append(this.controlId);
            ddcbuf.append('\',\'');
            ddcbuf.append(this.currentValues[entry].id);
            ddcbuf.append('\')">');
            if (this.isFiltered && this.keyedText) {
                var offset = this.currentValues[entry].lastMatchOffset;
                var bLen = this.currentValues[entry].lastMatchLength;
                if (offset > 0) {
                    ddcbuf.append(this.currentValues[entry].value.substr(0, offset));
                }
                ddcbuf.append('<b>');
                ddcbuf.append(this.currentValues[entry].value.substr(offset, bLen));
                ddcbuf.append('</b>');
                ddcbuf.append(this.currentValues[entry].value.substr(offset+bLen));
            } else {
                ddcbuf.append(this.currentValues[entry].value);
            }

            ddcbuf.append('</a>');
        }
        if (jQuery.browser.mozilla) {
            ddcbuf.append('<br/>');
        }
        this.dropDownDiv.html(ddcbuf.toString());
    }

    this.scrollAdjust = function() {
        var divHeight = this.dropDownDiv.height();
        var selRow = jQuery('#'+this.controlId+this.currentValues[this.keyScrollIdx].id);
        var rowHeight = selRow.outerHeight(true);
        if (selRow.position().top >= (divHeight-rowHeight)) {
            // push div so that selRow appears at bottom
            var scrollOffset = selRow.position().top-divHeight+rowHeight;
            this.dropDownDiv.scrollTop(this.dropDownDiv.scrollTop()+scrollOffset);
        } else if (selRow.position().top < 0) {
            // push div so that selrow appears at top
            this.dropDownDiv.scrollTop(this.dropDownDiv.scrollTop()+selRow.position().top);
        }
    }

    this.focus = function() {
        this.valueControl.focus();
    }

    this.defocus = function() {
        this.valueControl.blur();
    }

    this.open = function() {        
        this.isOpen = true;
        this.inOpen = true;
        var wrapper = jQuery(this.valueControl).parent();
        this.refreshValues();
        this.dropDownDiv.width(wrapper.width());
        this.dropDownDiv.css("top", wrapper.position().top + wrapper.outerHeight() + "px");
        this.dropDownDiv.css("left", wrapper.position().left + parseInt(wrapper.css("margin-left").replace("px", "")) + "px");
        this.dropDownDiv.show();
        // ie6 support
        if (this.dropDownDiv.bgiframe) {
            this.dropDownDiv.bgiframe();
        }
        this.inOpen = false;
    }

    this.close = function() {
        if (!this.inClose) {
            this.inClose = true;
        }
        this.isOpen = false;
        this.dropDownDiv.hide();
        this.defocus();
        this.inClose = false;
    }

    this.toggle = function() {
        if (this.isOpen) {
            this.close();
        } else {
            this.open();
        }
    }

    this.selectedId = function() {
        return this.idControl.val();
    }

    this.selectedValue = function() {
        return this.valueControl.val();
    }

    this.selectRowByDSIdx = function(dsIdx) {
        this.keyScrollIdx = -1;
        this.idControl.val(visural_datasources[this.dataSourceName].values[dsIdx].id);
        this.valueControl.val(visural_datasources[this.dataSourceName].values[dsIdx].value);
        this.keyedText = this.selectedValue();
//        visural_dropdowns[this.controlId].closeOnBlur = true;
//        this.defocus();
    }

    this.selectRow = function(entryId) {
        this.keyScrollIdx = -1;
        var dsIdx = visural_datasources[this.dataSourceName].indexForId(entryId);
        if (dsIdx > -1) {
            this.selectRowByDSIdx(dsIdx);
            this.onChangeCallback();
        }
    }

    this.acceptInput = function(mykey) {
        if (mykey == 38) {
            // up
            // unhilite if necessary
            if (this.keyScrollIdx != -1) {
                jQuery('#'+this.controlId+this.currentValues[this.keyScrollIdx].id).removeClass('selected');
            }
            this.keyScrollIdx -= 1;
            if (this.keyScrollIdx < 0) {
                this.keyScrollIdx = this.currentValues.length - 1;
            }
            this.idControl.val(this.currentValues[this.keyScrollIdx].id);
            this.valueControl.val(this.currentValues[this.keyScrollIdx].value);
            jQuery('#'+this.controlId+this.currentValues[this.keyScrollIdx].id).addClass('selected');
            this.scrollAdjust();
        } else if (mykey == 40) {
            // down
            // unhilite if necessary
            if (this.keyScrollIdx != -1) {
                jQuery('#'+this.controlId+this.currentValues[this.keyScrollIdx].id).removeClass('selected');
            }
            this.keyScrollIdx += 1;
            if (this.keyScrollIdx > this.currentValues.length - 1) {
                this.keyScrollIdx = 0;
            }
            this.idControl.val(this.currentValues[this.keyScrollIdx].id);
            this.valueControl.val(this.currentValues[this.keyScrollIdx].value);
            jQuery('#'+this.controlId+this.currentValues[this.keyScrollIdx].id).addClass('selected');
            this.scrollAdjust();
        } else if (mykey == 13) {
            // enter
            if (this.keyScrollIdx != -1) {
                this.selectRow(this.selectedId());
                window.setTimeout(function(cid) {
                    visural_dropdowns[cid].closeOnBlur = true;
                    visural_dropdowns[cid].close();
                }(this.controlId), 300);
            }
            this.refreshValues();
        } else {
            this.idControl.val(-1);
            this.keyScrollIdx = -1;
            this.keyedText = this.selectedValue();
            this.open();
            this.onChangeCallback();
        }
    }
}

function visural_dropdown_acceptinput(e, controlid, timestamp) {
    if (visural_dropdowns[controlid].lastInputTime <= timestamp) {
        visural_dropdowns[controlid].acceptInput(e);
    }
}

function visural_dropdown_filterinput(mykey) {
    if (mykey == 9 ||
        mykey == 16 ||
        mykey == 17 ||
        mykey == 18 ||
        mykey == 19 ||
        mykey == 20 ||
        mykey == 27 ||
        mykey == 33 ||
        mykey == 34 ||
        mykey == 35 ||
        mykey == 36 ||
        mykey == 37 ||
        mykey == 39 ||
        mykey == 45) {
        return false;
    } else {
        return true;
    }
}

function visural_dropdown_registerinput(e, controlid) {
    if (null == e) e = window.event ;
    var keyCode = e.keyCode; // fix for IE6
    if (visural_dropdown_filterinput(keyCode)) {
        var curTime = new Date().getTime();
        visural_dropdowns[controlid].lastInputTime = curTime;
        if (keyCode == 13 || keyCode == 40 || keyCode == 38) {
            // for special keys (up,down,enter) we want to refresh right away
            visural_dropdowns[controlid].acceptInput(keyCode);
        } else {
            // set a 300 millisecond buffer on input before list refresh (perf for large lists)
            window.setTimeout(function() {
                visural_dropdown_acceptinput(keyCode, controlid, curTime);
            }, 300);
        }
    }
    // stop form submission on enter (to select item)
    if (keyCode == 13) {
        return false;
    }
}

function visural_togglefilter_dropdown(controlid) {
    visural_dropdowns[controlid].toggleFilter();
    window.setTimeout(function() {
        visural_dropdowns[controlid].open();
    }, 100);
}
function visural_select_dropdown(controlid, rowId) {
    visural_dropdowns[controlid].selectRow(rowId);
    window.setTimeout(function() {
        visural_dropdowns[controlid].closeOnBlur = true;
        visural_dropdowns[controlid].close();
    }, 300);
}
function visural_dropdown_focus(controlid) {
    if (!visural_dropdowns[controlid].inOpen) {
        visural_dropdowns[controlid].open();
    }
}
function visural_dropdown_blur(controlid) {
    if (visural_dropdowns[controlid].closeOnBlur && !visural_dropdowns[controlid].inClose) {
        if (!visural_dropdowns[controlid].allowAnyValue) {
            if (visural_dropdowns[controlid].valueControl.val()) {
                if (visural_dropdowns[controlid].idControl.val() > -1) {
                    visural_dropdowns[controlid].selectRowByDSIdx(visural_dropdowns[controlid].idControl.val());
                } else {
                    visural_dropdowns[controlid].idControl.val('');
                    visural_dropdowns[controlid].valueControl.val('');
                }
            } else {
                // set id to blank
                visural_dropdowns[controlid].idControl.val('');
            }
        }
        visural_dropdowns[controlid].close();
    }
}
function visural_dropdown_toggle(controlid) {
    if (!visural_dropdowns[controlid].isOpen) {
        visural_dropdowns[controlid].focus();
    }
}
function visural_dropdown_mouseover(controlid) {
    visural_dropdowns[controlid].closeOnBlur = false;
}
function visural_dropdown_mouseout(controlid) {
    visural_dropdowns[controlid].closeOnBlur = true;
    // this is required as some browsers remove focus from an item when scrolling another e.g. dropdowndiv
    if (visural_dropdowns[controlid].isOpen) {
        visural_dropdowns[controlid].valueControl.focus();
    }
}
