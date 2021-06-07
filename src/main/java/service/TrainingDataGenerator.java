package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class TrainingDataGenerator {

    public static void main(String[] args) {
        createFiles("weka.txt");
        createFiles("weka.arff");
    }

    private static void createFiles(String path) {

        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File " + myObj.getName() + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(
                    "@relation teamSoftSkillCheck\n" +
                            "\n" +
                            "@attribute avgCommunicationScore numeric\n" +
                            "@attribute avgExperienceScore numeric\n" +
                            "@attribute hasVettedLeader {Yes,No}\n" +
                            "@attribute teamSynergyScore numeric\n" +
                            "@attribute passedScreening {Yes, No}\n" +
                            "\n" +
                            "@data\n" +
                            //Minimum communication
                            createData(100, 25, 100, 95, 100, "Yes", 95, 100, "Yes") +
                            createData(100, 0, 25, 95, 100, "Yes", 95, 100, "No") +
                            createData(100, 25, 30, 80, 95, "Yes", 95, 100, "No") +
                            createData(100, 25, 30, 95, 100, "Yes", 80, 95, "No") +

                            //Minimum communication with no team leader
                            createData(10, 50, 100, 90, 100, "No", 90, 100, "Yes") +
                            createData(100, 0, 50, 90, 100, "No", 90, 100, "No") +
                            createData(100, 50, 65, 80, 90, "No", 90, 100, "No") +
                            createData(100, 50, 65, 90, 100, "No", 80, 90, "No") +

                            //Very low communication with no team leader
                            createData(100, 65, 100, 80, 100, "No", 80, 100, "Yes") +
                            createData(100, 65, 75, 50, 80, "No", 50, 80, "Yes") +

                            //Very low communication with high experience and medium synergy
                            createData(100, 30, 100, 80, 100, "Yes", 50, 100, "Yes") +
                            createData(100, 30, 100, 80, 100, "Yes", 0, 50, "Yes") +

                            //Very low communication with high synergy and medium experience
                            createData(100, 30, 100, 50, 100, "Yes", 80, 100, "Yes") +
                            createData(100, 30, 100, 0, 50, "Yes", 80, 100, "No") +

                            //Low communication and synergy with high experience
                            createData(100, 35, 100, 80, 100, "Yes", 35, 100, "Yes") +

                            //Low communication with medium stats
                            createData(100, 35, 100, 50, 100, "Yes", 50, 100, "Yes") +

                            //No leader with medium stats
                            createData(100, 75, 100, 50, 100, "No", 50, 100, "Yes") +

                            //No leader with low experience
                            createData(100, 75, 100, 35, 100, "No", 80, 100, "Yes") +
                            createData(100, 75, 100, 0, 35, "No", 80, 100, "No") +

                            //No leader with low synergy
                            createData(100, 75, 100, 80, 100, "No", 35, 100, "Yes") +
                            createData(100, 75, 100, 80, 100, "No", 0, 35, "No") +

                            //Minimum experience
                            createData(100, 50, 100, 0, 100, "Yes", 65, 100, "Yes") +
                            createData(100, 50, 100, 0, 35, "Yes", 0, 65, "No") +
                            createData(100, 0, 50, 0, 35, "Yes", 65, 100, "No") +

                            //Minimum synergy
                            createData(100, 85, 100, 85, 100, "Yes", 25, 100, "Yes") +
                            createData(100, 80, 85, 85, 100, "Yes", 25, 35, "Yes") +
                            createData(100, 85, 100, 80, 85, "Yes", 25, 35, "No") +
                            createData(100, 85, 100, 85, 100, "Yes", 0, 25, "No")
            );
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static String createData(int num, int comMin, int comMax, int expMin, int expMax, String vet, int synMin, int synMax, String pas) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(getRandomNumber(comMin, comMax)).append("\t"); //avgCommunicationScore
            stringBuilder.append(getRandomNumber(expMin, expMax)).append("\t"); //avgExperienceScore
            stringBuilder.append(vet).append("\t");                             //hasVettedLeader
            stringBuilder.append(getRandomNumber(synMin, synMax)).append("\t"); //avgExperienceScore
            stringBuilder.append(pas).append("\n");                             //hasPassedScreening
        }
        return stringBuilder.toString();
    }

    private static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
