package com.carl.glideutilsdemo.bean;

import java.io.Serializable;

/**
 * 扩展数据对象
 *
 * @author zou.sq
 */
public class ExtendedBean implements Serializable {

    private int unreadMessageCount;
    private int unrewardedAward;
    private int unfinishedTasks;
    private String versionName;
    private String updateTime;
    private TabIcons tabIcons;

    public int getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(int unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public int getUnrewardedAward() {
        return unrewardedAward;
    }

    public void setUnrewardedAward(int unrewardedAward) {
        this.unrewardedAward = unrewardedAward;
    }

    public int getUnfinishedTasks() {
        return unfinishedTasks;
    }

    public void setUnfinishedTasks(int unfinishedTasks) {
        this.unfinishedTasks = unfinishedTasks;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public TabIcons getTabIcons() {
        return tabIcons;
    }

    public void setTabIcons(TabIcons tabIcons) {
        this.tabIcons = tabIcons;
    }

    public static class TabIcons {

        private String bgColor;
        private String index;
        private String indexActive;
        private String site;
        private String siteActive;
        private String pd;
        private String pdActive;
        private String discover;
        private String discoverActive;
        private String my;
        private String myActive;

        public TabIcons() {
        }

        public TabIcons(String index, String indexActive, String site, String siteActive, String pd, String pdActive, String discover, String discoverActive, String my, String myActive) {
            this.index = index;
            this.indexActive = indexActive;
            this.site = site;
            this.siteActive = siteActive;
            this.pd = pd;
            this.pdActive = pdActive;
            this.discover = discover;
            this.discoverActive = discoverActive;
            this.my = my;
            this.myActive = myActive;
        }

        public String getBgColor() {
            return bgColor;
        }

        public void setBgColor(String bgColor) {
            this.bgColor = bgColor;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getIndexActive() {
            return indexActive;
        }

        public void setIndexActive(String indexActive) {
            this.indexActive = indexActive;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getSiteActive() {
            return siteActive;
        }

        public void setSiteActive(String siteActive) {
            this.siteActive = siteActive;
        }

        public String getDiscover() {
            return discover;
        }

        public void setDiscover(String discover) {
            this.discover = discover;
        }

        public String getDiscoverActive() {
            return discoverActive;
        }

        public void setDiscoverActive(String discoverActive) {
            this.discoverActive = discoverActive;
        }

        public String getMy() {
            return my;
        }

        public void setMy(String my) {
            this.my = my;
        }

        public String getMyActive() {
            return myActive;
        }

        public void setMyActive(String myActive) {
            this.myActive = myActive;
        }

        public String getPd() {
            return pd;
        }

        public void setPd(String pd) {
            this.pd = pd;
        }

        public String getPdActive() {
            return pdActive;
        }

        public void setPdActive(String pdActive) {
            this.pdActive = pdActive;
        }

        @Override
        public String toString() {
            return "TabIcons{" +
                    "bgColor='" + bgColor + '\'' +
                    ", index='" + index + '\'' +
                    ", indexActive='" + indexActive + '\'' +
                    ", site='" + site + '\'' +
                    ", siteActive='" + siteActive + '\'' +
                    ", discover='" + discover + '\'' +
                    ", discoverActive='" + discoverActive + '\'' +
                    ", my='" + my + '\'' +
                    ", myActive='" + myActive + '\'' +
                    '}';
        }
    }
}
