package com.sefrWahed.apeye.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abdel-Rahman El-Shikh
 */
public class PredictionResponse implements Parcelable {

    @SerializedName("flowers")
    @Expose
    private String flowers;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("diseasename")
    @Expose
    private String diseasename;
    @SerializedName("scientificname")
    @Expose
    private String scientificname;
    @SerializedName("hostlist")
    @Expose
    private List<String> hostlist = null;
    @SerializedName("diseasetype")
    @Expose
    private String diseasetype;
    @SerializedName("nutshel")
    @Expose
    private List<String> nutshel = null;
    @SerializedName("symptoms")
    @Expose
    private String symptoms;
    @SerializedName("trigger")
    @Expose
    private String trigger;
    @SerializedName("biocontrol")
    @Expose
    private String biocontrol;
    @SerializedName("chemcontrol")
    @Expose
    private String chemcontrol;
    @SerializedName("preventivemesures")
    @Expose
    private List<String> preventivemesures = null;

    protected PredictionResponse(Parcel in) {
        flowers = in.readString();
        url = in.readString();
        diseasename = in.readString();
        scientificname = in.readString();
        hostlist = in.createStringArrayList();
        diseasetype = in.readString();
        nutshel = in.createStringArrayList();
        symptoms = in.readString();
        trigger = in.readString();
        biocontrol = in.readString();
        chemcontrol = in.readString();
        preventivemesures = in.createStringArrayList();
    }

    public static final Creator<PredictionResponse> CREATOR = new Creator<PredictionResponse>() {
        @Override
        public PredictionResponse createFromParcel(Parcel in) {
            return new PredictionResponse(in);
        }

        @Override
        public PredictionResponse[] newArray(int size) {
            return new PredictionResponse[size];
        }
    };

    public String getFlowers() {
        return flowers;
    }

    public void setFlowers(String flowers) {
        this.flowers = flowers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename;
    }

    public String getScientificname() {
        return scientificname;
    }

    public void setScientificname(String scientificname) {
        this.scientificname = scientificname;
    }

    public List<String> getHostlist() {
        return hostlist;
    }

    public void setHostlist(List<String> hostlist) {
        this.hostlist = hostlist;
    }

    public String getDiseasetype() {
        return diseasetype;
    }

    public void setDiseasetype(String diseasetype) {
        this.diseasetype = diseasetype;
    }

    public List<String> getNutshel() {
        return nutshel;
    }

    public void setNutshel(List<String> nutshel) {
        this.nutshel = nutshel;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getBiocontrol() {
        return biocontrol;
    }

    public void setBiocontrol(String biocontrol) {
        this.biocontrol = biocontrol;
    }

    public String getChemcontrol() {
        return chemcontrol;
    }

    public void setChemcontrol(String chemcontrol) {
        this.chemcontrol = chemcontrol;
    }

    public List<String> getPreventivemesures() {
        return preventivemesures;
    }

    public void setPreventivemesures(List<String> preventivemesures) {
        this.preventivemesures = preventivemesures;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(flowers);
        parcel.writeString(url);
        parcel.writeString(diseasename);
        parcel.writeString(scientificname);
        parcel.writeStringList(hostlist);
        parcel.writeString(diseasetype);
        parcel.writeStringList(nutshel);
        parcel.writeString(symptoms);
        parcel.writeString(trigger);
        parcel.writeString(biocontrol);
        parcel.writeString(chemcontrol);
        parcel.writeStringList(preventivemesures);
    }
}
