package com.example.lenovo.myapplication.fragments;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.myapplication.FristActivity;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.database.DBOpenHelper;
import com.example.lenovo.myapplication.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/4/26.
 */

public class FragmentRecite extends Fragment implements View.OnClickListener{

    DBOpenHelper dbOpenHelper;
    TextView tv_word,tv_translate;
    Button button_renshi,button_burenshi,button_next;
    ArrayList<Word> words;
    int i = 0;   //背诵数量
    int a = 1;   //删除数量
    //创建一个视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbOpenHelper = new DBOpenHelper(getActivity(),"tb_dict",null,1);


        View view = inflater.inflate(R.layout.fragment_fragment_reccite,null);
        return view;
    }

    //创建时显示
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        words = getWords();
        String word = null;
        try {
            word = words.get(i).word;
        }
        catch (Exception e){
            ToastUtil.showMsg(getActivity(),"单词库中没有数据");
        }
        tv_word = view.findViewById(R.id.recite_tv_word);
        tv_translate = view.findViewById(R.id.recite_tv_translate);
        button_renshi = view.findViewById(R.id.recite_btn_renshi);
        button_burenshi = view.findViewById(R.id.recite_btn_burenshi);
        button_next = view.findViewById(R.id.recite_btn_next);
        setListener();

        tv_word.setText(word);
    }

    private void setListener()
    {
        button_next.setOnClickListener(this);
        button_burenshi.setOnClickListener(this);
        button_renshi.setOnClickListener(this);
    }

    private ArrayList<Word> getWords(){
        ArrayList<Word> words = new ArrayList<>();
        Cursor cursor = dbOpenHelper.getReadableDatabase().query("tb_dict",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            Word word = new Word();
            //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
            word.word = cursor.getString(cursor.getColumnIndex("word"));
            word.translate = cursor.getString(cursor.getColumnIndex("translate"));
            words.add(word);
        }
        return words;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recite_btn_renshi: {   //点击认识，删除单词，跳入下一个单词
                if (words.size() == 0)//如果单词库为空
                {
                    ToastUtil.showMsg(getActivity(), "已全部背完啦！");
                } else if (words.size() == i+1) { //如果单词到最后一个
                    words.remove(i);
                    ToastUtil.showMsg(getActivity(), "已全部背完啦！");
                    tv_translate.setText("");
                    tv_word.setText("");
                } else if(words.size() == i) {  //删掉最后一个后的i处理
                    ToastUtil.showMsg(getActivity(), "已全部背完啦！");
                }else{    //如果单词不是最后一个
                    words.remove(i);
                    ToastUtil.showMsg(getActivity(), "删除成功");
                    i++;
                    i = i - a;
                    tv_word.setText(words.get(i).word);
                    tv_translate.setText("");
                }
                dbOpenHelper.onUpgrade(dbOpenHelper.getReadableDatabase(), 0, 0);   //更新数据库
                for (Word word : words) {
                    dbOpenHelper.writeData(dbOpenHelper.getReadableDatabase(), word.word, word.translate);
                }
                break;
            }
            case R.id.recite_btn_burenshi:{     //点击不认识，显示翻译
                if(words.size()==0){
                    ToastUtil.showMsg(getActivity(), "已全部背完啦！");
                }else{
                    tv_translate.setText(words.get(i).translate);
                }
                break;
            }
            case R.id.recite_btn_next:{     //点击下一个，显示下一个单词
                if (i >= words.size()-1)    //如果是最后一个
                {
                    ToastUtil.showMsg(getActivity(),"已全部背完啦！");
                }else{
                    i++;
                    tv_word.setText(words.get(i).word);
                    tv_translate.setText("");
                }
                break;
            }
        }
    }
    class Word{
        String word;
        String translate;
    }
}
