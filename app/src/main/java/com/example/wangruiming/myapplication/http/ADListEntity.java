package com.example.wangruiming.myapplication.http;

import java.util.List;

public class ADListEntity {

    private List<ListBean> beginAdList;

    /**
     * title : 首页弹出广告
     * linkUrl : http://xdy.ittun.com/mobile/wap/safeGuard?device=app
     * imgPath : http://xdy.ittun.com/mobile/images/mobile/ios/banner/bigdata-20160419.jpg
     * adType : 0
     * destId : 0
     */

    private List<ListBean> firstAdList;
    /**
     * 垂直广告位
     */
    private List<ListBean> textAdList;
    /**
     * title : 产品详情广告
     * linkUrl : http://xdy.ittun.com/mobile/wap/safeGuard?device=app
     * imgPath : http://xdy.ittun.com/mobile/images/mobile/ios/banner/bigdata-20160419.jpg
     * adType : 0
     * destId : 0
     */

    private List<ListBean> proAdList;


    private List<ListBean> guideList;

    public List<ListBean> getBeginAdList() {
        return beginAdList;
    }

    public void setBeginAdList(List<ListBean> beginAdList) {
        this.beginAdList = beginAdList;
    }

    public List<ListBean> getFirstAdList() {
        return firstAdList;
    }

    public void setFirstAdList(List<ListBean> firstAdList) {
        this.firstAdList = firstAdList;
    }

    public List<ListBean> getTextAdList() {
        return textAdList;
    }

    public void setTextAdList(List<ListBean> textAdList) {
        this.textAdList = textAdList;
    }

    public List<ListBean> getProAdList() {
        return proAdList;
    }

    public void setProAdList(List<ListBean> proAdList) {
        this.proAdList = proAdList;
    }

    public List<ListBean> getGuideList() {
        return guideList;
    }

    public void setGuideList(List<ListBean> guideList) {
        this.guideList = guideList;
    }

    /**
     * bannerList : [{"adType":1,"destId":0,"imgPath":"https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20171221202337212.png","linkUrl":"https://tstmobile.gqichina.net/h5/h51.0/activity/rotateCard/index.html?endTime=2018_01_27&device=android&ver=4.7.2&mobileSysVer=6.0&deviceId=864105033973449&appModel=M5Note","id":1111,"title":"签到"},{"adType":1,"destId":0,"imgPath":"https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20171217155035342.jpg","linkUrl":"https://tstmobile.gqichina.net/h5/h51.0/signin/index.html?device=android&ver=4.7.2&mobileSysVer=6.0&deviceId=864105033973449&appModel=M5Note","id":1108,"title":"到签日没"},{"adType":1,"destId":0,"imgPath":"https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20171220114905534.png","linkUrl":" https://mobile.gqget.com/h5/h51.0/downAPP/updateApp.html?partner.com&device=android&ver=4.7.2&mobileSysVer=6.0&deviceId=864105033973449&appModel=M5Note","id":1110,"title":"首页领取成功h5"},{"adType":1,"destId":0,"imgPath":"https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20170627104943527.png","linkUrl":"http://10.100.32.28:3000/#/appInteractive?path=2017yearbill&device=android&ver=4.7.2&mobileSysVer=6.0&deviceId=864105033973449&appModel=M5Note","id":857,"title":"2周年APP"},{"adType":1,"destId":0,"imgPath":"https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20170829180520635.jpg","linkUrl":"","id":1030,"title":"jiaxing"},{"adType":1,"destId":0,"imgPath":"https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20170623101908506.jpg","linkUrl":"http://mtest.gqhmt.com/#/appInteractive?path=2017yearbill&device=android&ver=4.7.2&mobileSysVer=6.0&deviceId=864105033973449&appModel=M5Note","id":826,"title":"200亿页面"},{"adType":1,"destId":0,"imgPath":"https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20170510162837123.jpg","linkUrl":"https://tstmobile.gqichina.net/h5/h51.0/financialClass/detail.html?newsId=587&device=android&ver=4.7.2&mobileSysVer=6.0&deviceId=864105033973449&appModel=M5Note","id":587,"title":"首投送礼首投送礼首投送礼首投送礼首投送礼首投送礼首投送礼首投送礼礼首投送首投送礼首投送礼"},{"adType":1,"destId":0,"imgPath":"https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20170426152818692.png","linkUrl":"https://phonetest.gqhmt.com/h5/h51.0/activity/DanFan/index.html?device=android&ver=4.7.2&mobileSysVer=6.0&deviceId=864105033973449&appModel=M5Note","id":585,"title":"开宝箱"}]
     * isupload : 1
     */

    private String isupload;
    private List<ListBean> bannerList;
    private List<String> refreshContent;

    public List<String> getRefreshContent() {
        return refreshContent;
    }

    public void setRefreshContent(List<String> refreshContent) {
        this.refreshContent = refreshContent;
    }

    public String getIsupload() {
        return isupload;
    }

    public void setIsupload(String isupload) {
        this.isupload = isupload;
    }

    public List<ListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<ListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public static class ListBean {
        /**
         * adType : 1
         * destId : 0
         * imgPath : https://tstmobile.gqichina.net/GQGETfiles/news/gq_news_20171221202337212.png
         * linkUrl : https://tstmobile.gqichina.net/h5/h51.0/activity/rotateCard/index.html?endTime=2018_01_27&device=android&ver=4.7.2&mobileSysVer=6.0&deviceId=864105033973449&appModel=M5Note
         * id : 1111
         * title : 签到
         */

        private int adType;
        private int destId;
        private String imgPath;
        private String linkUrl;
        private int id;
        private String title;
        private String publishTime;
        private String shortTitle;

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getShortTitle() {
            return shortTitle;
        }

        public void setShortTitle(String shortTitle) {
            this.shortTitle = shortTitle;
        }

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

        public int getDestId() {
            return destId;
        }

        public void setDestId(int destId) {
            this.destId = destId;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}