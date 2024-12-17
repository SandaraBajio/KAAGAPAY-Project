package managers;

import db.MissionDatabase;
import entities.Mission;

import java.util.Scanner;

public class MissionManager {
    private MissionDatabase missionDatabase;

    public MissionManager(MissionDatabase missionDatabase) {
        this.missionDatabase = missionDatabase;
    }

    public void showMissionNames() {
        var missions = missionDatabase.getAllMissions();

        if (missions.isEmpty()) {
            System.out.println("No missions available.");
            return;
        }

        System.out.println("\n--- Available Missions ---");
        int index = 1;
        for (Mission mission : missions) {
            System.out.println(index + ". " + mission.getMissionName());
            index++;
        }
    }

    public Mission findMissionByName(String name) {
        return missionDatabase.getMissionByName(name);
    }

    public void viewAllMission() {
        for (Mission mission : missionDatabase.getAllMissions()) {
            System.out.println(mission);
            System.out.println("Operation Type: " + mission.getOperationType());
        }
    }

    public void updateMissionStatus(String name, String newStatus) {
        Mission mission = findMissionByName(name);
        if (mission != null) {
            mission.setStatus(Mission.MissionStatus.valueOf(newStatus.toUpperCase()));
            missionDatabase.updateMission(mission);
            System.out.println("Mission status updated for " + name + " to: " + newStatus);
        }
    }

    public void deleteMission(String name) {
        Mission mission = findMissionByName(name);
        if (mission != null) {
            missionDatabase.deleteMission(mission.getMissionId());
            System.out.println("Mission deleted: " + name);
        }
    }

    public void addMission() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter mission name: ");
        String name = scanner.nextLine();

        System.out.print("Enter mission type (e.g., EARTHQUAKE, TYPHOON, FIRE, etc.): ");
        String typeInput = scanner.nextLine();
        Mission.MissionType type = Mission.MissionType.valueOf(typeInput.toUpperCase());

        System.out.print("Enter operation type (RELIEF or RESCUE): ");
        String operationInput = scanner.nextLine();
        Mission.OperationType operationType = Mission.OperationType.valueOf(operationInput.toUpperCase());

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.print("Enter mission status (ONGOING or COMPLETED): ");
        String statusInput = scanner.nextLine();
        Mission.MissionStatus status = Mission.MissionStatus.valueOf(statusInput.toUpperCase());

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Mission newMission = new Mission(name, type, operationType, location, status, description);
        missionDatabase.insertMission(newMission);
        System.out.println("New mission successfully added!");
    }
}
