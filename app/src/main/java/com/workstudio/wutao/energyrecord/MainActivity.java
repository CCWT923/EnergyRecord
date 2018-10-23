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
import android.widget.Toast;

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

        txt_ele_charge = findViewById(R.id.ele_charge);
        txt_water_charge = findViewById(R.id.water_charge);
        txt_guolu_charge1 = findViewById(R.id.gas_guolu_charge);
        txt_guolu_charge2 = findViewById(R.id.gas_guolu_charge2);
        txt_kitchen_charge1 = findViewById(R.id.gas_kitchen_charge);
        txt_kitchen_charge2 = findViewById(R.id.gas_kitchen_charge2);
        txt_gas_chargeSum = findViewById(R.id.gas_chargeSum);

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
    EditText txt_ele_charge;
    EditText txt_water_charge;
    EditText txt_guolu_charge1;
    EditText txt_guolu_charge2;
    EditText txt_kitchen_charge1;
    EditText txt_kitchen_charge2;
    EditText txt_gas_chargeSum;

    TextView view_title;
    double charge_total = 0.0;
    double priceOfWater = 4.43;
    double priceOfEle = 0.85;
    double priceOfGas = 2.98;

    @Override
    public void onClick(View v)
    {
        String t;
        //用电量
        if(ele_value.getText().length() > 0 && ele_lastValue.getText().length() > 0)
        {
            //保留两位小数
            t = new DecimalFormat("0").format(((Double.valueOf(ele_value.getText().toString()) - Double.valueOf(ele_lastValue.getText().toString())) * 1000));
            ele_use.setText(t);
            txt_ele_charge.setText( String.valueOf(Double.valueOf(t) * priceOfEle));
        }
        //厨房气表1
        if(gas_kitchen_value1.getText().length() > 0 && gas_kitchen_lastValue.getText().length ()> 0)
        {
            t = String.valueOf((Double.valueOf(gas_kitchen_value1.getText().toString()) - Double.valueOf(gas_kitchen_lastValue.getText().toString())));
            gas_kitchen_use1.setText(t);
            txt_kitchen_charge1.setText(String.valueOf(Double.valueOf(t) * priceOfGas));
        }
        //厨房气表2
        if(gas_kitchen_value2.getText().length() > 0 && gas_kitchen_lastValue2.getText().length ()> 0)
        {
            t = String.valueOf((Double.valueOf(gas_kitchen_value2.getText().toString()) - Double.valueOf(gas_kitchen_lastValue2.getText().toString())));
            gas_kitchen_use2.setText(t);
            txt_kitchen_charge2.setText(String.valueOf(Double.valueOf(t) * priceOfGas));
        }
        //锅炉气表1
        if(gas_guolu_value1.getText().length() > 0 && gas_guolu_lastValue.getText().length ()> 0)
        {
            t = String.valueOf((Double.valueOf(gas_guolu_value1.getText().toString()) - Double.valueOf(gas_guolu_lastValue.getText().toString())));
            gas_guolu_use1.setText(t);
            txt_guolu_charge1.setText(String.valueOf(Double.valueOf(t) * priceOfGas));
        }
        //锅炉气表2
        if(gas_guolu_value2.getText().length() > 0 && gas_guolu_lastValue2.getText().length ()> 0)
        {
            t = String.valueOf((Double.valueOf(gas_guolu_value2.getText().toString()) - Double.valueOf(gas_guolu_lastValue2.getText().toString())));
            gas_guolu_use2.setText(t);
            txt_guolu_charge2.setText(String.valueOf(Double.valueOf(t) * priceOfGas));
        }
        //水表
        if(water_value.getText().length() > 0 && water_lastValue.getText().length ()> 0)
        {
            t = String.valueOf((Double.valueOf(water_value.getText().toString()) - Double.valueOf(water_lastValue.getText().toString())));
            water_use.setText(t);
            txt_water_charge.setText(String.valueOf(Double.valueOf(t)* priceOfWater));
        }

        try {
            txt_gas_chargeSum.setText(new DecimalFormat("0.00").format(
                    Double.valueOf(txt_guolu_charge1.getText().toString())
                            + Double.valueOf(txt_guolu_charge2.getText().toString())
                            + Double.valueOf(txt_kitchen_charge1.getText().toString())
                            + Double.valueOf(txt_kitchen_charge2.getText().toString())

            ));

            charge_total = Double.valueOf(txt_gas_chargeSum.getText().toString()) + Double.valueOf(txt_ele_charge.getText().toString()) + Double.valueOf(txt_water_charge.getText().toString());
            editText_charge_total.setText(new DecimalFormat("0.00").format((charge_total)));
        }catch (Exception ex)
        {
            //显示消息
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
