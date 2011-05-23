/* 
 *  Copyright 2010 Richard Nichols.
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
 */

var visural_inputHints = [];

function VisuralInputHint(p_inputId, p_hintText, p_hintStyle, p_entryStyle) {
    this.inputId = p_inputId;
    this.hintText = p_hintText;
    this.hintStyle = p_hintStyle;
    this.entryStyle = p_entryStyle;

    this.handleFocus = function() {
        if (jQuery('#'+this.inputId).val() === this.hintText) {
            jQuery('#'+this.inputId).attr('style', this.entryStyle);
            jQuery('#'+this.inputId).val('');
        }
    }
    this.handleBlur = function() {
        if (!jQuery('#'+this.inputId).val()) {
            jQuery('#'+this.inputId).attr('style', this.hintStyle);
            jQuery('#'+this.inputId).val(this.hintText);
        }
    }
}