package entities;

public class Volunteer extends User {
    private boolean isApprovedForMission;
    private String missionName;

    public Volunteer(String username, String missionName) {
        super(username, "");
        this.isApprovedForMission = false;
        this.missionName = missionName;
    }

    public boolean isApprovedForMission() {
        return isApprovedForMission;
    }

    public void setApprovedForMission(boolean approvedForMission) {
        isApprovedForMission = approvedForMission;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    @Override
    public String toString() {
        return "Volunteer: " + getUsername() + ", Mission: " + missionName;
    }
}
