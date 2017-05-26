/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CorpusModel;
import model.PunctuationModel;
import model.StopWordsIndonesianModel;
import model.TermModel;
import IndonesianStemmer.IndonesianStemmer;
/**
 *
 * @author mfahry
 */
public class PreProcess {
    CorpusModel corpusMod;
    StopWordsIndonesianModel stopWordsMod;
    PunctuationModel puncMod;
    TermModel termMod;
    Map<String, Integer> terms;
    
    public PreProcess() {
        corpusMod = new CorpusModel();
        stopWordsMod = new StopWordsIndonesianModel();
        puncMod = new PunctuationModel();
        termMod = new TermModel();
        terms = new HashMap<String, Integer>();
    }

    public void stopWords(){
        try {
            //ambil semua stop words untuk digunakan dalam menghapusnya dalam corpus
            ResultSet stopWords = stopWordsMod.selectAll();
            
            while(stopWords.next()){
                //update corpus untuk menghilangkan setiap kata yang mengandung stop words
                corpusMod.updateCorpusforPreProcess(" "+stopWords.getString("word")+" ");
            }
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cleanPunctuation(){
        try {
            //ambil semua punctuation untuk digunakan dalam menghapusnya dalam corpus
            ResultSet punctuations = puncMod.selectAll();
            
            while(punctuations.next()){
                
                //update corpus untuk menghilangkan setiap kata yang mengandung punctuations
                corpusMod.updateCorpusforPreProcess(punctuations.getString("punc"));
            }
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void lowerCaseCorpus(){
        try {
            //update corpus ke lowercase
            corpusMod.updateCorpusLowerCase();
        } 
        catch (Exception ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void wordSegmentation(String sentence){
        String tempTerms[] = sentence.split(" ");

        for(int i = 0 ; i<tempTerms.length; i++){
            if(!terms.containsKey(tempTerms[i])){
                terms.put(tempTerms[i], 0);
            }
        }
    }
    
    public void generateTerms(){
        try {
            ResultSet corpus = corpusMod.selectCorpus();
            
            while(corpus.next()){
                wordSegmentation(corpus.getString("corpus_value"));
            }
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void saveTerms(){
        for(String term : terms.keySet()){
            termMod.insertTerm(term);
        }
    }
    
    public void stemming(){
        try {
            ResultSet termAll = termMod.selectTerm();
            IndonesianStemmer stemmer = new IndonesianStemmer();
            String resultStem = null;
            
            while(termAll.next()){
                System.out.println(termAll.getString("term"));
                
                    try{
                        resultStem = stemmer.findRootWord(termAll.getString("term"));
                    }
                    catch(StringIndexOutOfBoundsException e){
                        System.out.println("eeee");
                    }
                
                if(resultStem == null){
                    resultStem = termAll.getString("term");
                }
                corpusMod.updateCorpusforStemming(" "+termAll.getString("term")+" ", " "+resultStem+" ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PreProcess.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
}
