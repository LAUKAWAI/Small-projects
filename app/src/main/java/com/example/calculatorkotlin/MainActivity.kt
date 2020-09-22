package com.example.calculatorkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var SaveA = 0
    var SaveB = 0
    val KEY_SAVE_A = "key_saveA"
    val KEY_SAVE_B = "key_saveB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        read()

        button_a_add1.setOnClickListener {
            addA(1)
        }
        button_a_add2.setOnClickListener {
            addA(2)
        }
        button_a_add3.setOnClickListener {
            addA(3)
        }

        button_b_add1.setOnClickListener {
            addB(1)
        }
        button_b_add2.setOnClickListener {
            addB(2)
        }
        button_b_add3.setOnClickListener {
            addB(3)
        }
        //撤销上一步
        image_Button_revoke.setOnClickListener {
            revoke()
        }
        //重置比分
        image_Button_reset.setOnClickListener {
            reset()
        }
    }


    fun save() {//保存比分
        SaveA = text_score_a.text.toString().toInt()
        SaveB = text_score_b.text.toString().toInt()
        val sp = getSharedPreferences("my_sp", MODE_PRIVATE) //创建sp对象
        sp.edit().putInt(KEY_SAVE_A, SaveA).apply() //提交要存储的数据
        sp.edit().putInt(KEY_SAVE_B, SaveB).apply() //提交要存储的数据
    }

    private fun read() {//读取比分
        val sp = getSharedPreferences("my_sp", MODE_PRIVATE) //创建sp对象
        SaveA = sp.getInt(KEY_SAVE_A, 0)
        SaveB = sp.getInt(KEY_SAVE_B, 0)
        text_score_a.text = SaveA.toString()
        text_score_b.text = SaveB.toString()
    }

    fun addA(i: Int) {  //A队加分
        save()
        text_score_a.text = (SaveA + i).toString()
    }

    fun addB(i: Int) {  //B队加分
        save()
        text_score_b.text = (SaveB + i).toString()
    }

    fun revoke() {   //撤销上一次分数
        text_score_a.text = SaveA.toString()
        text_score_b.text = SaveB.toString()
    }

    fun reset() {    //重置比分
        save()
        text_score_a.text = "0"
        text_score_b.text = "0"
    }

    override fun onPause() {
        super.onPause()
        //        Log.d(TAG, "onPause(),界面离开前台，可见但不可交互");
        save()
    }
}