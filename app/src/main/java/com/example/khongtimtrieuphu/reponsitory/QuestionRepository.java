package com.example.khongtimtrieuphu.reponsitory;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.khongtimtrieuphu.database.QuestionDAO;
import com.example.khongtimtrieuphu.database.QuestionRoomDatabase;
import com.example.khongtimtrieuphu.model.Question;

public class QuestionRepository {

    private QuestionDAO mQuestionDAO;
    private MutableLiveData<Question> question = new MutableLiveData<>();

    public QuestionRepository(Application application){
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(application);
        mQuestionDAO = db.questionDAO();

    }

    private static class GetDataAsyncTask extends AsyncTask<Integer, Void, Question> {

        private QuestionDAO asyncTaskDAO;

        private QuestionRepository delegate = null;

        GetDataAsyncTask(QuestionDAO dao){
            asyncTaskDAO = dao;
        }

        @Override
        protected Question doInBackground(Integer... integers) {
            return asyncTaskDAO.getAllQuestion(integers[0]);
        }

        @Override
        protected void onPostExecute(Question question) {
            delegate.question.setValue(question);
        }
    }

    public void getData(int level){
        GetDataAsyncTask task = new GetDataAsyncTask(mQuestionDAO);
        task.delegate = this;
        task.execute(level);
    }


    public LiveData<Question> getAllQuestion() {
        return question;
    }
}
