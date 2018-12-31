package com.kuanhsien.hellonoodoe.object;

public class UserInfo {

    /**
     * objectId : WNfAIJO3v6
     * username : test2@qq.com
     * timezone : 3.5
     * email : nick@abc.com
     * code : YAH0M2PF
     * createdAt : 2018-03-15T03:06:43.411Z
     * updatedAt : 2018-12-28T07:42:23.119Z
     * reportEmail : test@abc.com
     * reportmail : gary@test.com
     * ACL : {"WNfAIJO3v6":{"read":true,"write":true}}
     * sessionToken : r:15caab7cac4bcaea193197c62f99d258
     */

    private String objectId;
    private String username;
    private double timezone;
    private String email;
    private String code;
    private String createdAt;
    private String updatedAt;
    private String reportEmail;
    private String reportmail;
    private ACLBean ACL;
    private String sessionToken;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTimezone() {
        return timezone;
    }

    public void setTimezone(double timezone) {
        this.timezone = timezone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getReportEmail() {
        return reportEmail;
    }

    public void setReportEmail(String reportEmail) {
        this.reportEmail = reportEmail;
    }

    public String getReportmail() {
        return reportmail;
    }

    public void setReportmail(String reportmail) {
        this.reportmail = reportmail;
    }

    public ACLBean getACL() {
        return ACL;
    }

    public void setACL(ACLBean ACL) {
        this.ACL = ACL;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public static class ACLBean {
        /**
         * WNfAIJO3v6 : {"read":true,"write":true}
         */

        private WNfAIJO3v6Bean WNfAIJO3v6;

        public WNfAIJO3v6Bean getWNfAIJO3v6() {
            return WNfAIJO3v6;
        }

        public void setWNfAIJO3v6(WNfAIJO3v6Bean WNfAIJO3v6) {
            this.WNfAIJO3v6 = WNfAIJO3v6;
        }

        public static class WNfAIJO3v6Bean {
            /**
             * read : true
             * write : true
             */

            private boolean read;
            private boolean write;

            public boolean isRead() {
                return read;
            }

            public void setRead(boolean read) {
                this.read = read;
            }

            public boolean isWrite() {
                return write;
            }

            public void setWrite(boolean write) {
                this.write = write;
            }
        }
    }
}
