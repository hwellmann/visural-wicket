/*
 *  Copyright 2010 Visural.
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
package com.visural.wicket.util;

import com.visural.common.IOUtil;
import com.visural.common.StringUtil;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a Wicket version ID and allows comparison. Also, provides a method
 * for attempting to detect the current version.
 * @author Visural
 */
public class WicketVersion implements Serializable, Comparable<WicketVersion> {
    private static final long serialVersionUID = 1L;
    
    /**
     * Attempts to detect wicket version from Maven POM properties
     * @return
     */
    public static WicketVersion detect() {
        Properties pomProperties = new Properties();
        InputStream propStream = null;
        try {            
            propStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/maven/org.apache.wicket/wicket/pom.properties");
            pomProperties.load(propStream);
        } catch (Throwable t) {
            Logger.getLogger(WicketVersion.class.getName()).log(Level.SEVERE, "Error reading properties from META-INF/maven/org.apache.wicket/wicket/pom.properties", t);
        } finally {
            IOUtil.silentClose(WicketVersion.class, propStream);
        }
        return new WicketVersion(pomProperties.getProperty("version"));
    }

    private final String version;
    private int major;
    private int minor;
    private int incremental;

    public WicketVersion(String version) {
        this.version = version;
        if (StringUtil.isNotBlankStr(version)) {
            try {
                String[] sa = version.split("\\.");
                if (sa.length == 3) {
                    major = Integer.parseInt(sa[0]);
                    minor = Integer.parseInt(sa[1]);
                    incremental = Integer.parseInt(sa[2]);
                } else {
                    throw new IllegalArgumentException("Expected version M.m.i");
                }
            } catch (Throwable t) {
                major = 0;
                minor = 0;
                incremental = 0;
            }
        } else {
            major = 0;
            minor = 0;
            incremental = 0;
        }
    }

    public int getIncremental() {
        return incremental;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WicketVersion other = (WicketVersion) obj;
        if ((this.version == null) ? (other.version != null) : !this.version.equals(other.version)) {
            return false;
        }
        if (this.major != other.major) {
            return false;
        }
        if (this.minor != other.minor) {
            return false;
        }
        if (this.incremental != other.incremental) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 59 * hash + this.major;
        hash = 59 * hash + this.minor;
        hash = 59 * hash + this.incremental;
        return hash;
    }

    public int compareTo(WicketVersion otherVersion) {
        return Integer.valueOf(getCompareVersion()).compareTo(Integer.valueOf(otherVersion.getCompareVersion()));
    }

    private int getCompareVersion() {
        return major*1000+minor*100+incremental;
    }

    public boolean after(WicketVersion ver) {
        return this.compareTo(ver) > 0;
    }

    public boolean before(WicketVersion ver) {
        return this.compareTo(ver) < 0;
    }
   
}
