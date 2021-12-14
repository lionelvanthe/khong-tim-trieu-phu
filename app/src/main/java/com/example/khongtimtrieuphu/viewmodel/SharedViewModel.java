package com.example.khongtimtrieuphu.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.khongtimtrieuphu.Preference;
import com.example.khongtimtrieuphu.model.AmountOfMoney;
import com.example.khongtimtrieuphu.model.Question;
import com.example.khongtimtrieuphu.reponsitory.QuestionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SharedViewModel extends BaseViewModel{

    private MutableLiveData<List<AmountOfMoney>> amountOfMoney = new MutableLiveData<>();
    private int trueCase;
    private int level;
    private String money;
    private String name;
    public float[] percentCase = new float[4];

    private Preference preference;


    private QuestionRepository repository;
    private LiveData<Question> result;

    public SharedViewModel(@NonNull Application application) {
        super(application);
        repository = new QuestionRepository(application);
        result = repository.getAllQuestion();
        preference = new Preference(application);

    }

    public void getQuestion(int level) {
        repository.getData(level);
    }

    public LiveData<Question> getResult() {
        return result;
    }

    public List<AmountOfMoney> getListAmountOfMoney() {
        List<AmountOfMoney> list = new ArrayList<>();
        list.add(new AmountOfMoney("150.000.000"));
        list.add(new AmountOfMoney("85.000.000"));
        list.add(new AmountOfMoney("60.000.000"));
        list.add(new AmountOfMoney("40.000.000"));
        list.add(new AmountOfMoney("30.000.000"));
        list.add(new AmountOfMoney("22.000.000"));
        list.add(new AmountOfMoney("14.000.000"));
        list.add(new AmountOfMoney("10.000.000"));
        list.add(new AmountOfMoney("6.000.000"));
        list.add(new AmountOfMoney("3.000.000"));
        list.add(new AmountOfMoney("2.000.000"));
        list.add(new AmountOfMoney("1.000.000"));
        list.add(new AmountOfMoney("600.000"));
        list.add(new AmountOfMoney("400.000"));
        list.add(new AmountOfMoney("200.000"));
        return list;
    }

    public void randomPercentCase(){
        Random random = new Random();
        percentCase[trueCase -1] = random.nextInt(52 -50) + 50;
        float temp = 100 - percentCase[trueCase -1];

        if(trueCase != 4){
            for(int i=0;i<3;i++){
                if(i != trueCase -1 ){
                    percentCase[i] = random.nextFloat()*(temp-4);
                    temp-=percentCase[i];
                }
                percentCase[3] = temp;

            }
        }
        else{
            for(int i = 0 ; i< 3; i++){
                if(i != 2){
                    percentCase[i] = random.nextFloat()*(temp-4);
                    temp -= percentCase[i];
                }
                percentCase[2] = temp;
            }
        }
    }

    public String getStringTrueCase(){
        switch (trueCase){
            case 1:
                return "A. ";
            case 2:
                return "B. ";
            case 3:
                return "C. ";
            case 4:
                return "D. ";
            default:
                return "";
        }

    }


    public MutableLiveData<List<AmountOfMoney>> getAmountOfMoney() {
        return amountOfMoney;
    }

    public void  changeIsSelected(int n, boolean isSelected){
        List<AmountOfMoney> list = getListAmountOfMoney();
        list.get(n).setSelected(isSelected);
        amountOfMoney.postValue(list);
    }

    public Preference getPreference() {
        return preference;
    }


    public int getTrueCase() {
        return trueCase;
    }

    public void setTrueCase(int trueCase) {
        this.trueCase = trueCase;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
