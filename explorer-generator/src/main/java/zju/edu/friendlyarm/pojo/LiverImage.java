package zju.edu.friendlyarm.pojo;

public class LiverImage {
    private Integer id;

    private Double doctorNum;

    private Double patientNum;

    private String ralativePath;

    private String imageName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDoctorNum() {
        return doctorNum;
    }

    public void setDoctorNum(Double doctorNum) {
        this.doctorNum = doctorNum;
    }

    public Double getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(Double patientNum) {
        this.patientNum = patientNum;
    }

    public String getRalativePath() {
        return ralativePath;
    }

    public void setRalativePath(String ralativePath) {
        this.ralativePath = ralativePath == null ? null : ralativePath.trim();
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }
}