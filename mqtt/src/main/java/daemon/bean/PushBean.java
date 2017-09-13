package daemon.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dys on 2017/9/8 0008.
 */
public class PushBean implements Serializable{

    /**
     * alarm : {"id":"1d143f47e2b648ef93bdd1676fa505d7","alertLevel":"WARN","alarmType":"IdcMatch","similarity":0,"checkRecord":{"idcInfoDto":{"name":"胡显立","idcCode":"412721198911262250","gender":"男","age":27,"birthDate":"1989年11月26日","ethnicity":"汉","address":"河南省扶沟县古城乡古城街","idcImageUrl":"D:\\program\\winsion-independence\\alarm-images\\2017-09-08\\0\\0\\0\\42-1-41272119891126225020170814094458046.bmp","bureau":"扶沟县公安局","validTerm":"2017.01.25-2037.01.25"},"travelInfoDto":{"id":"","trainNumber":"--","passengerName":"--","carriage":"--","seatType":"--","seatNumber":"--","ticketType":"--","departStation":"--","destinationStation":"--","ticketDepartTime":0,"enteringTime":0,"departTime":0,"waitingRoom":"--","checkPort":"--","platform":"--"},"caughtImageUrlList":[],"takingTrain":"","similarity":0,"entrance":"济南西-东进站口-自动入站口","deviceId":"001","checkResult":"READ_TICKET_FAILED","checkTime":1502675096469,"checkCostTime":0,"devicePassengerCount":0},"targetDto":{"id":"20b29489959248ef81fde64a22d6540b","targetName":"关注目标A","targetLevel":"WARN","targetBehaviour":"程序开发人员","personName":"胡显立","personIdcCode":"412721198911262250","imageList":[]},"checkImageUrlList":[],"alarmTime":1504862684250,"acknowledgeTime":0,"completeTime":0}
     * pendingAlert : 23
     * alertThisDay : 22
     */

    private AlarmBean alarm;
    private long pendingAlert;
    private long alertThisDay;

    public AlarmBean getAlarm() {
        return alarm;
    }

    public void setAlarm(AlarmBean alarm) {
        this.alarm = alarm;
    }

    public long getPendingAlert() {
        return pendingAlert;
    }

    public void setPendingAlert(long pendingAlert) {
        this.pendingAlert = pendingAlert;
    }

    public long getAlertThisDay() {
        return alertThisDay;
    }

    public void setAlertThisDay(long alertThisDay) {
        this.alertThisDay = alertThisDay;
    }

    public static class AlarmBean implements Serializable{
        /**
         * id : 1d143f47e2b648ef93bdd1676fa505d7
         * alertLevel : WARN
         * alarmType : IdcMatch
         * similarity : 0.0
         * checkRecord : {"idcInfoDto":{"name":"胡显立","idcCode":"412721198911262250","gender":"男","age":27,"birthDate":"1989年11月26日","ethnicity":"汉","address":"河南省扶沟县古城乡古城街","idcImageUrl":"D:\\program\\winsion-independence\\alarm-images\\2017-09-08\\0\\0\\0\\42-1-41272119891126225020170814094458046.bmp","bureau":"扶沟县公安局","validTerm":"2017.01.25-2037.01.25"},"travelInfoDto":{"id":"","trainNumber":"--","passengerName":"--","carriage":"--","seatType":"--","seatNumber":"--","ticketType":"--","departStation":"--","destinationStation":"--","ticketDepartTime":0,"enteringTime":0,"departTime":0,"waitingRoom":"--","checkPort":"--","platform":"--"},"caughtImageUrlList":[],"takingTrain":"","similarity":0,"entrance":"济南西-东进站口-自动入站口","deviceId":"001","checkResult":"READ_TICKET_FAILED","checkTime":1502675096469,"checkCostTime":0,"devicePassengerCount":0}
         * targetDto : {"id":"20b29489959248ef81fde64a22d6540b","targetName":"关注目标A","targetLevel":"WARN","targetBehaviour":"程序开发人员","personName":"胡显立","personIdcCode":"412721198911262250","imageList":[]}
         * checkImageUrlList : []
         * alarmTime : 1504862684250
         * acknowledgeTime : 0
         * completeTime : 0
         */

        private String id;
        private String alertLevel;
        private String alarmType;
        private double similarity;
        private CheckRecordBean checkRecord;
        private TargetDtoBean targetDto;
        private long alarmTime;
        private long acknowledgeTime;
        private long completeTime;
        private List<?> checkImageUrlList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAlertLevel() {
            return alertLevel;
        }

        public void setAlertLevel(String alertLevel) {
            this.alertLevel = alertLevel;
        }

        public String getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(String alarmType) {
            this.alarmType = alarmType;
        }

        public double getSimilarity() {
            return similarity;
        }

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }

        public CheckRecordBean getCheckRecord() {
            return checkRecord;
        }

        public void setCheckRecord(CheckRecordBean checkRecord) {
            this.checkRecord = checkRecord;
        }

        public TargetDtoBean getTargetDto() {
            return targetDto;
        }

        public void setTargetDto(TargetDtoBean targetDto) {
            this.targetDto = targetDto;
        }

        public long getAlarmTime() {
            return alarmTime;
        }

        public void setAlarmTime(long alarmTime) {
            this.alarmTime = alarmTime;
        }

        public long getAcknowledgeTime() {
            return acknowledgeTime;
        }

        public void setAcknowledgeTime(long acknowledgeTime) {
            this.acknowledgeTime = acknowledgeTime;
        }

        public long getCompleteTime() {
            return completeTime;
        }

        public void setCompleteTime(long completeTime) {
            this.completeTime = completeTime;
        }

        public List<?> getCheckImageUrlList() {
            return checkImageUrlList;
        }

        public void setCheckImageUrlList(List<?> checkImageUrlList) {
            this.checkImageUrlList = checkImageUrlList;
        }

        public static class CheckRecordBean implements Serializable{
            /**
             * idcInfoDto : {"name":"胡显立","idcCode":"412721198911262250","gender":"男","age":27,"birthDate":"1989年11月26日","ethnicity":"汉","address":"河南省扶沟县古城乡古城街","idcImageUrl":"D:\\program\\winsion-independence\\alarm-images\\2017-09-08\\0\\0\\0\\42-1-41272119891126225020170814094458046.bmp","bureau":"扶沟县公安局","validTerm":"2017.01.25-2037.01.25"}
             * travelInfoDto : {"id":"","trainNumber":"--","passengerName":"--","carriage":"--","seatType":"--","seatNumber":"--","ticketType":"--","departStation":"--","destinationStation":"--","ticketDepartTime":0,"enteringTime":0,"departTime":0,"waitingRoom":"--","checkPort":"--","platform":"--"}
             * caughtImageUrlList : []
             * takingTrain :
             * similarity : 0.0
             * entrance : 济南西-东进站口-自动入站口
             * deviceId : 001
             * checkResult : READ_TICKET_FAILED
             * checkTime : 1502675096469
             * checkCostTime : 0
             * devicePassengerCount : 0
             */

            private IdcInfoDtoBean idcInfoDto;
            private TravelInfoDtoBean travelInfoDto;
            private String takingTrain;
            private double similarity;
            private String entrance;
            private String deviceId;
            private String checkResult;
            private long checkTime;
            private long checkCostTime;
            private long devicePassengerCount;
            private List<?> caughtImageUrlList;

            public IdcInfoDtoBean getIdcInfoDto() {
                return idcInfoDto;
            }

            public void setIdcInfoDto(IdcInfoDtoBean idcInfoDto) {
                this.idcInfoDto = idcInfoDto;
            }

            public TravelInfoDtoBean getTravelInfoDto() {
                return travelInfoDto;
            }

            public void setTravelInfoDto(TravelInfoDtoBean travelInfoDto) {
                this.travelInfoDto = travelInfoDto;
            }

            public String getTakingTrain() {
                return takingTrain;
            }

            public void setTakingTrain(String takingTrain) {
                this.takingTrain = takingTrain;
            }

            public double getSimilarity() {
                return similarity;
            }

            public void setSimilarity(double similarity) {
                this.similarity = similarity;
            }

            public String getEntrance() {
                return entrance;
            }

            public void setEntrance(String entrance) {
                this.entrance = entrance;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getCheckResult() {
                return checkResult;
            }

            public void setCheckResult(String checkResult) {
                this.checkResult = checkResult;
            }

            public long getCheckTime() {
                return checkTime;
            }

            public void setCheckTime(long checkTime) {
                this.checkTime = checkTime;
            }

            public long getCheckCostTime() {
                return checkCostTime;
            }

            public void setCheckCostTime(long checkCostTime) {
                this.checkCostTime = checkCostTime;
            }

            public long getDevicePassengerCount() {
                return devicePassengerCount;
            }

            public void setDevicePassengerCount(long devicePassengerCount) {
                this.devicePassengerCount = devicePassengerCount;
            }

            public List<?> getCaughtImageUrlList() {
                return caughtImageUrlList;
            }

            public void setCaughtImageUrlList(List<?> caughtImageUrlList) {
                this.caughtImageUrlList = caughtImageUrlList;
            }

            public static class IdcInfoDtoBean {
                /**
                 * name : 胡显立
                 * idcCode : 412721198911262250
                 * gender : 男
                 * age : 27
                 * birthDate : 1989年11月26日
                 * ethnicity : 汉
                 * address : 河南省扶沟县古城乡古城街
                 * idcImageUrl : D:\program\winsion-independence\alarm-images\2017-09-08\0\0\0\42-1-41272119891126225020170814094458046.bmp
                 * bureau : 扶沟县公安局
                 * validTerm : 2017.01.25-2037.01.25
                 */

                private String name;
                private String idcCode;
                private String gender;
                private long age;
                private String birthDate;
                private String ethnicity;
                private String address;
                private String idcImageUrl;
                private String bureau;
                private String validTerm;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIdcCode() {
                    return idcCode;
                }

                public void setIdcCode(String idcCode) {
                    this.idcCode = idcCode;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public long getAge() {
                    return age;
                }

                public void setAge(long age) {
                    this.age = age;
                }

                public String getBirthDate() {
                    return birthDate;
                }

                public void setBirthDate(String birthDate) {
                    this.birthDate = birthDate;
                }

                public String getEthnicity() {
                    return ethnicity;
                }

                public void setEthnicity(String ethnicity) {
                    this.ethnicity = ethnicity;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getIdcImageUrl() {
                    return idcImageUrl;
                }

                public void setIdcImageUrl(String idcImageUrl) {
                    this.idcImageUrl = idcImageUrl;
                }

                public String getBureau() {
                    return bureau;
                }

                public void setBureau(String bureau) {
                    this.bureau = bureau;
                }

                public String getValidTerm() {
                    return validTerm;
                }

                public void setValidTerm(String validTerm) {
                    this.validTerm = validTerm;
                }
            }

            public static class TravelInfoDtoBean {
                /**
                 * id :
                 * trainNumber : --
                 * passengerName : --
                 * carriage : --
                 * seatType : --
                 * seatNumber : --
                 * ticketType : --
                 * departStation : --
                 * destinationStation : --
                 * ticketDepartTime : 0
                 * enteringTime : 0
                 * departTime : 0
                 * waitingRoom : --
                 * checkPort : --
                 * platform : --
                 */

                private String id;
                private String trainNumber;
                private String passengerName;
                private String carriage;
                private String seatType;
                private String seatNumber;
                private String ticketType;
                private String departStation;
                private String destinationStation;
                private long ticketDepartTime;
                private long enteringTime;
                private long departTime;
                private String waitingRoom;
                private String checkPort;
                private String platform;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTrainNumber() {
                    return trainNumber;
                }

                public void setTrainNumber(String trainNumber) {
                    this.trainNumber = trainNumber;
                }

                public String getPassengerName() {
                    return passengerName;
                }

                public void setPassengerName(String passengerName) {
                    this.passengerName = passengerName;
                }

                public String getCarriage() {
                    return carriage;
                }

                public void setCarriage(String carriage) {
                    this.carriage = carriage;
                }

                public String getSeatType() {
                    return seatType;
                }

                public void setSeatType(String seatType) {
                    this.seatType = seatType;
                }

                public String getSeatNumber() {
                    return seatNumber;
                }

                public void setSeatNumber(String seatNumber) {
                    this.seatNumber = seatNumber;
                }

                public String getTicketType() {
                    return ticketType;
                }

                public void setTicketType(String ticketType) {
                    this.ticketType = ticketType;
                }

                public String getDepartStation() {
                    return departStation;
                }

                public void setDepartStation(String departStation) {
                    this.departStation = departStation;
                }

                public String getDestinationStation() {
                    return destinationStation;
                }

                public void setDestinationStation(String destinationStation) {
                    this.destinationStation = destinationStation;
                }

                public long getTicketDepartTime() {
                    return ticketDepartTime;
                }

                public void setTicketDepartTime(long ticketDepartTime) {
                    this.ticketDepartTime = ticketDepartTime;
                }

                public long getEnteringTime() {
                    return enteringTime;
                }

                public void setEnteringTime(long enteringTime) {
                    this.enteringTime = enteringTime;
                }

                public long getDepartTime() {
                    return departTime;
                }

                public void setDepartTime(long departTime) {
                    this.departTime = departTime;
                }

                public String getWaitingRoom() {
                    return waitingRoom;
                }

                public void setWaitingRoom(String waitingRoom) {
                    this.waitingRoom = waitingRoom;
                }

                public String getCheckPort() {
                    return checkPort;
                }

                public void setCheckPort(String checkPort) {
                    this.checkPort = checkPort;
                }

                public String getPlatform() {
                    return platform;
                }

                public void setPlatform(String platform) {
                    this.platform = platform;
                }
            }
        }

        public static class TargetDtoBean implements Serializable{
            /**
             * id : 20b29489959248ef81fde64a22d6540b
             * targetName : 关注目标A
             * targetLevel : WARN
             * targetBehaviour : 程序开发人员
             * personName : 胡显立
             * personIdcCode : 412721198911262250
             * imageList : []
             */

            private String id;
            private String targetName;
            private String targetLevel;
            private String targetBehaviour;
            private String personName;
            private String personIdcCode;
            private List<?> imageList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTargetName() {
                return targetName;
            }

            public void setTargetName(String targetName) {
                this.targetName = targetName;
            }

            public String getTargetLevel() {
                return targetLevel;
            }

            public void setTargetLevel(String targetLevel) {
                this.targetLevel = targetLevel;
            }

            public String getTargetBehaviour() {
                return targetBehaviour;
            }

            public void setTargetBehaviour(String targetBehaviour) {
                this.targetBehaviour = targetBehaviour;
            }

            public String getPersonName() {
                return personName;
            }

            public void setPersonName(String personName) {
                this.personName = personName;
            }

            public String getPersonIdcCode() {
                return personIdcCode;
            }

            public void setPersonIdcCode(String personIdcCode) {
                this.personIdcCode = personIdcCode;
            }

            public List<?> getImageList() {
                return imageList;
            }

            public void setImageList(List<?> imageList) {
                this.imageList = imageList;
            }
        }
    }
}
