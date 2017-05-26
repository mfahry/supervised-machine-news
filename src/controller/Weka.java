/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CorpusModel;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomialUpdateable;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.rules.DTNB;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author mfahry
 */
public class Weka {
    CorpusModel cm;
    FastVector attributes;
    Instances dataSet;
    FilteredClassifier classifier;
    Instances filteredData;
    StringToWordVector stwv;
    double[] values;
    String[] resultNaiveBayes;
    String[] resultMultinomial;
    public Weka() {
        attributes = new FastVector();
        cm = new CorpusModel();
        classifier = new FilteredClassifier();
    }
    
    public void generateArff(){
        attributes.addElement(new Attribute("corpus_value", (FastVector) null)); 
        
        FastVector classAttr = new FastVector();
        classAttr.addElement("netral");
        classAttr.addElement("non-netral");
        attributes.addElement(new Attribute("class", classAttr)); 

        dataSet = new Instances("Berita", attributes, 0);
        
        
        ResultSet rs = cm.selectCorpus();
        try {
            while(rs.next()){
                double[] vals = new double[dataSet.numAttributes()];
                vals[0] = dataSet.attribute(0).addStringValue(rs.getString("corpus_value"));
                vals[1] = classAttr.indexOf(rs.getString("class"));
                dataSet.add(new Instance(1.0, vals));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Weka.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataSet.setClassIndex(dataSet.numAttributes() - 1);
    }
    
    public void generateFeature(){
        stwv = new StringToWordVector(10000);
        try {
            stwv.setInputFormat(dataSet);
        } catch (Exception ex) {
            Logger.getLogger(Weka.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            filteredData = StringToWordVector.useFilter(dataSet, stwv);
            System.out.println(filteredData.toString());
        } catch (Exception ex) {
            Logger.getLogger(Weka.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void classifyUsingNaiveBayes(){
        try {
            classifier.setFilter(stwv);
            classifier.setClassifier(new BayesNet());
            
            Evaluation eval = new Evaluation(dataSet);
            eval.crossValidateModel(classifier, dataSet, 10, new Random(10));
            System.out.println(eval.toSummaryString());
            System.out.println("\nClass Detailnya: ");
            System.out.println("\n"+eval.toClassDetailsString());
            System.out.println("\nCumulative Margin Distribution: ");
            System.out.println("\n"+eval.toCumulativeMarginDistributionString());
            System.out.println("\nMatrix: ");
            System.out.println("\n"+eval.toMatrixString());
            
            resultNaiveBayes = new String[7];
            resultNaiveBayes[0] = eval.toSummaryString();
            resultNaiveBayes[1] = "\nClass Detailnya:";
            resultNaiveBayes[2] = "\n"+eval.toClassDetailsString();
            resultNaiveBayes[3] = "\nCumulative Margin Distribution: ";
            resultNaiveBayes[4] = "\n"+eval.toCumulativeMarginDistributionString();
            resultNaiveBayes[5] = "\nMatrix: ";
            resultNaiveBayes[6] = "\n"+eval.toMatrixString();
            
        } catch (Exception ex) {
            Logger.getLogger(Weka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void classifyUsingMultinomialNaiveBayes(){
        try {
            classifier.setFilter(stwv);
            classifier.setClassifier(new NaiveBayesMultinomialUpdateable());
            
            Evaluation eval = new Evaluation(dataSet);
            eval.crossValidateModel(classifier, dataSet, 10, new Random(10));
            System.out.println(eval.toSummaryString());
            System.out.println("\nClass Detailnya: ");
            System.out.println("\n"+eval.toClassDetailsString());
            System.out.println("\nCumulative Margin Distribution: ");
            System.out.println("\n"+eval.toCumulativeMarginDistributionString());
            System.out.println("\nMatrix: ");
            System.out.println("\n"+eval.toMatrixString());
            
            
            resultMultinomial = new String[7];
            resultMultinomial[0] = eval.toSummaryString();
            resultMultinomial[1] = "\nClass Detailnya:";
            resultMultinomial[2] = "\n"+eval.toClassDetailsString();
            resultMultinomial[3] = "\nCumulative Margin Distribution: ";
            resultMultinomial[4] = "\n"+eval.toCumulativeMarginDistributionString();
            resultMultinomial[5] = "\nMatrix: ";
            resultMultinomial[6] = "\n"+eval.toMatrixString();
        } catch (Exception ex) {
            Logger.getLogger(Weka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] getResultNaiveBayes(){
        return resultNaiveBayes;
    }
    
    public String[] getResultMultinomial(){
        return resultMultinomial;
    }
    
    public String getDataArff(){
        return filteredData.toString();
    }
}
