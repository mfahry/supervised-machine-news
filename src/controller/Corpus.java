/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CorpusModel;

/**
 *
 * @author mfahry
 */
public class Corpus {
    CorpusModel corpusMod;
    String corpusValue;
    String corpusLabel;

    public Corpus() {
    }
    
    public Corpus(String corpusValue, String corpusLabel) {
        this.corpusMod = new CorpusModel();
        this.corpusValue = corpusValue;
        this.corpusLabel = corpusLabel;
    }

    public String getCorpusValue() {
        return corpusValue;
    }

    public void setCorpusValue(String corpusValue) {
        this.corpusValue = corpusValue;
    }

    public String getCorpusLabel() {
        return corpusLabel;
    }

    public void setCorpusLabel(String corpusLabel) {
        this.corpusLabel = corpusLabel;
    }
    
    public void insert(){
        corpusMod.insertCorpus(this.corpusValue, this.corpusLabel);
    }
    
    // yang lain untuk update, delete, select nanti ya
}
