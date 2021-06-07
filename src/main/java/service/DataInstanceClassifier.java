package service;

import dtos.TeamDTO;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
import weka.core.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class DataInstanceClassifier {

    private Classifier cls = null;

    public static void main(String[] args) {
        DataInstanceClassifier dIC = new DataInstanceClassifier();

        //dIC.createJ48Model();
        dIC.EvaluateModel("J48.model");
        //dIC.testClassifier(new TeamDTO(20, 56, "Yes", 78));
    }

    private void testClassifier(TeamDTO teamDTO) {
        Boolean result = getTeamClassification(teamDTO);

        System.out.println("Team evaluation result:");
        if (result == null) {
            System.out.println("Error");
        } else if (result) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public Boolean getTeamClassification(TeamDTO teamDTO) {
        double result = classify(teamDTO);
        if (result == 1.0) {
            return false;
        }
        if (result == 0.0) {
            return true;
        }
        return null;
    }

    private double classify(TeamDTO teamDTO) {
        double result = -1;
        try {
            ArrayList<String> classVal = new ArrayList<>();
            classVal.add("Yes");
            classVal.add("No");

            Attribute avgCommunicationScore = new Attribute("avgCommunicationScore");
            Attribute avgExperienceScore = new Attribute("avgExperienceScore");
            Attribute hasVettedLeader = new Attribute("hasVettedLeader", classVal);
            Attribute teamSynergyScore = new Attribute("teamSynergyScore");
            Attribute hasPassed = new Attribute("@@class@@", classVal);

            ArrayList<Attribute> attributeList = new ArrayList<>();
            attributeList.add(avgCommunicationScore);
            attributeList.add(avgExperienceScore);
            attributeList.add(hasVettedLeader);
            attributeList.add(teamSynergyScore);
            attributeList.add(hasPassed);

            Instances dataset = new Instances("TestInstances", attributeList, 1);
            dataset.setClassIndex(dataset.numAttributes() - 1);

            DenseInstance instance = new DenseInstance(dataset.numAttributes());
            instance.setDataset(dataset);
            dataset.add(instance);
            instance.setValue(avgCommunicationScore, teamDTO.getAvgCommunicationScore());
            instance.setValue(avgExperienceScore, teamDTO.getAvgExperienceScore());
            instance.setValue(hasVettedLeader, teamDTO.getHasVettedLeader());
            instance.setValue(teamSynergyScore, teamDTO.getTeamSynergyScore());

            if (cls == null) {
                cls = (Classifier) SerializationHelper.read("J48.model");
            }
            result = cls.classifyInstance(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void createJ48Model() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("weka.arff"));

            Instances train = new Instances(bufferedReader);
            train.setClassIndex(train.numAttributes() - 1);

            bufferedReader.close();

            J48 jTree = new J48();
            jTree.buildClassifier(train);

            SerializationHelper.write("J48.model", jTree);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EvaluateModel(String modelPath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("weka.arff"));

            Instances train = new Instances(bufferedReader);
            train.setClassIndex(train.numAttributes() - 1);

            bufferedReader.close();

            Classifier cls = (Classifier) SerializationHelper.read(modelPath);

            Evaluation eval = new Evaluation(train);
            eval.crossValidateModel(cls, train, 10, new Random(1));

            System.out.println(eval.toSummaryString("Results:\n", true));
            System.out.println(eval.fMeasure(1) + " " + eval.precision(1) + " " + eval.recall(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
