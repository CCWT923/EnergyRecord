package com.workstudio.wutao.energyrecord;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉顶部标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //requestWindowFeature(Window.FEATURE_ACTION_BAR);
        //全屏显示
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        btn_calc = findViewById(R.id.btn_calc);
        //登录监听对象，以this表示当前对象
        btn_calc.setOnClickListener(this);

        water_value = (EditText)findViewById(R.id.water_value);
        water_use = findViewById(R.id.water_use);
        water_lastValue = findViewById(R.id.water_lastValue);

        gas_kitchen_use1 = findViewById(R.id.gas_kitchen_use);
        gas_kitchen_use2 = findViewById(R.id.gas_kitchen_use2);
        gas_kitchen_lastValue = findViewById(R.id.gas_kitchen_lastValue);
        gas_kitchen_value1 = findViewById(R.id.gas_kitchen_value);
        gas_kitchen_value2 = findViewById(R.id.gas_kitchen_value2);
        gas_kitchen_lastValue2 = findViewById(R.id.gas_kitchen_lastValue2);

        gas_guolu_use1 = findViewById(R.id.gas_guolu_use);
        gas_guolu_use2 = findViewById(R.id.gas_guolu_use2);
        gas_guolu_value1 = findViewById(R.id.gas_guolu_value);
        gas_guolu_value2 = findViewById(R.id.gas_guolu_value2);
        gas_guolu_lastValue = findViewById(R.id.gas_guolu_lastValue);
        gas_guolu_lastValue2 = findViewById(R.id.gas_guolu_lastValue2);

        ele_use = findViewById(R.id.ele_useValue);
        ele_value = findViewById(R.id.ele_value);
        ele_lastValue = findViewById(R.id.ele_lastValue);
        editText_charge_total = findViewById(R.id.money_total);

        view_title = findViewById(R.id.title);
        //获取当前日期
        /* 方法一
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年M月d日");
        Date date = new Date(System.currentTimeMillis());
        view_title.setText(dateFormat.format(date) + " 能耗记录");
        */
        //方法二
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        view_title.setText(String.valueOf(year) + "年" + String.valueOf(month) + "月" + String.valueOf(day) + "日 能耗记录" );

    }

    ActionBar actionBar;
    Button btn_calc;
    EditText water_value;
    EditText water_use;
    EditText gas_kitchen_value1;
    EditText gas_kitchen_value2;
    EditText gas_kitchen_use1;
    EditText gas_kitchen_use2;
    EditText gas_guolu_value1;
    EditText gas_guolu_value2;
    EditText gas_guolu_use1;
    EditText gas_guolu_use2;
    EditText ele_use;
    EditText ele_value;
    EditText editText_charge_total;
    EditText water_lastValue;
    EditText ele_lastValue;
    EditText gas_kitchen_lastValue;
    EditText gas_kitchen_lastValue2;
    EditText gas_guolu_lastValue;
    EditText gas_guolu_lastValue2;

    TextView view_title;
    double charge_guolu = 0.0;
    double charge_kichen = 0.0;
    double charge_water = 0.0;
    double charge_ele = 0.0;
    double charge_total = 0.0;
    double priceOfWater = 4.43;
    double priceOfEle = 0.85;
    double priceOfGas = 2.98;

    @Override
    public void onClick(View v)
    {
        //用电量
        if(ele_value.getText().length() > 0 && ele_lastValue.getText().length() > 0)
        {
            //保留两位小数
            ele_use.setText(new DecimalFormat("0.00").format(((Double.valueOf(ele_value.getText().toString()) - Double.valueOf(ele_lastValue.getText().toString())) * 1000)));
        }
        //厨房气表1
        if(gas_kitchen_value1.getText().length() > 0 && gas_kitchen_lastValue.getText().length ()> 0)
        {
            gas_kitchen_use1.setText(String.valueOf((Double.valueOf(gas_kitchen_value1.getText().toString()) - Double.valueOf(gas_kitchen_lastValue.getText().toString()))));
        }
        //厨房气表2
        if(gas_kitchen_value2.getText().length() > 0 && gas_kitchen_lastValue2.getText().length ()> 0)
        {
            gas_kitchen_use2.setText(String.valueOf((Double.valueOf(gas_kitchen_value2.getText().toString()) - Double.valueOf(gas_kitchen_lastValue2.getText().toString()))));
        }
        //锅炉气表1
        if(gas_guolu_value1.getText().length() > 0 && gas_guolu_lastValue.getText().length ()> 0)
        {
            gas_guolu_use1.setText(String.valueOf((Double.valueOf(gas_guolu_value1.getText().toString()) - Double.valueOf(gas_guolu_lastValue.getText().toString()))));
        }
        //锅炉气表2
        if(gas_guolu_value2.getText().length() > 0 && gas_guolu_lastValue2.getText().length ()> 0)
        {
            gas_guolu_use2.setText(String.valueOf((Double.valueOf(gas_guolu_value2.getText().toString()) - Double.valueOf(gas_guolu_lastValue2.getText().toString()))));
        }
        //水表
        if(water_value.getText().length() > 0 && water_lastValue.getText().length ()> 0)
        {
            water_use.setText(String.valueOf((Double.valueOf(water_value.getText().toString()) - Double.valueOf(water_lastValue.getText().toString()))));
        }

        if(water_use.getText().length() > 0)
        {
            charge_water = Double.valueOf(water_use.getText().toString()) * priceOfWater;
        }
        if(ele_use.getText().length() > 0)
        {
            charge_ele = Double.valueOf(ele_use.getText().toString()) * priceOfEle;
        }
        if(gas_guolu_use2.getText().length() > 0 && gas_guolu_use1.getText().length() > 0)
        {
            charge_guolu = (Double.valueOf(gas_guolu_use1.getText().toString()) + Double.valueOf(gas_guolu_use2.getText().toString())) * priceOfGas;
        }
        if(gas_kitchen_use1.getText().length() > 0 && gas_kitchen_use2.getText().length() > 0)
        {
            charge_kichen = (Double.valueOf(gas_kitchen_use1.getText().toString()) + Double.valueOf(gas_kitchen_use2.getText().toString())) * priceOfGas;
        }

        charge_total = charge_ele + charge_water + charge_kichen + charge_guolu;
        editText_charge_total.setText(new DecimalFormat("0.00").format((charge_total)));
    }
}
