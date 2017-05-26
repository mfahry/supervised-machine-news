/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.Home;


/**
 *
 * @author mfahry
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        new Home().setVisible(true);
        //tahap preprocessing
        
        /*PreProcess pp = new PreProcess();
        /*pp.stopWords();
        pp.cleanPunctuation();
        pp.lowerCaseCorpus();
        pp.generateTerms();
        pp.saveTerms();*/
//        pp.stemming();*/
//        FastVector attributes;
//        Instances dataSet;
//        double[] values;
//        attributes = new FastVector();
//        
//        attributes.addElement(new Attribute("corpus_value", (FastVector) null)); 
//        
//        FastVector classAttr = new FastVector();
//        classAttr.addElement("netral");
//        classAttr.addElement("non-netral");
//        attributes.addElement(new Attribute("class", classAttr)); 
//
//        dataSet = new Instances("Berita", attributes, 0);
//        
//        CorpusModel cm = new CorpusModel();
//        
//        ResultSet rs = cm.selectCorpus();
//        while(rs.next()){
//            double[] vals = new double[dataSet.numAttributes()];
//            vals[0] = dataSet.attribute(0).addStringValue(rs.getString("corpus_value"));
//            vals[1] = classAttr.indexOf(rs.getString("class"));
//            dataSet.add(new Instance(1.0, vals));
//        }
//
////        values = new double[dataSet.n]; 
////        values[0] = 3;
////        values[1] =7;
////        values[3] = 1;
////        dataSet.add(new Instance(1.0, values));
////
////        values = new double[dataSet.numAttributes()];   
////        values[2] = 2;
////        values[3] = 8;
////        dataSet.add(new Instance(1.0, values));
//
//        dataSet.setClassIndex(dataSet.numAttributes() - 1);
//        
//        StringToWordVector stwv = new StringToWordVector(10000);
//        stwv.setInputFormat(dataSet);
//        Instances filteredData = StringToWordVector.useFilter(dataSet, stwv);
//        
//        System.out.println(filteredData.toString());
//        
//        
//        FilteredClassifier classifier = new FilteredClassifier();
//        classifier.setFilter(stwv);
//        classifier.setClassifier(new NaiveBayes());
//            
//            
//            Evaluation eval = new Evaluation(dataSet);
//            eval.crossValidateModel(classifier, dataSet, 10, new Random(10));
//            System.out.println(eval.toSummaryString());
//            System.out.println("\nClass Detailnya: ");
//            System.out.println("\n"+eval.toClassDetailsString());
//            System.out.println("\nCumulative Margin Distribution: ");
//            System.out.println("\n"+eval.toCumulativeMarginDistributionString());
//            System.out.println("\nMatrix: ");
//            System.out.println("\n"+eval.toMatrixString());
//        
//            
//        classifier = new FilteredClassifier();
//        classifier.setFilter(stwv);
//        classifier.setClassifier(new NaiveBayesMultinomialUpdateable());
//            
//            
//            eval = new Evaluation(dataSet);
//            eval.crossValidateModel(classifier, dataSet, 10, new Random(10));
//            System.out.println(eval.toSummaryString());
//            System.out.println("\nClass Detailnya: ");
//            System.out.println("\n"+eval.toClassDetailsString());
//            System.out.println("\nCumulative Margin Distribution: ");
//            System.out.println("\n"+eval.toCumulativeMarginDistributionString());
//            System.out.println("\nMatrix: ");
//            System.out.println("\n"+eval.toMatrixString());
    }
    
}
