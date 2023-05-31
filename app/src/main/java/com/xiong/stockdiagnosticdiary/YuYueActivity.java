package com.xiong.stockdiagnosticdiary;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.xiong.stockdiagnosticdiary.bean.Hairstylist;
import com.xiong.stockdiagnosticdiary.bean.Member;
import com.xiong.stockdiagnosticdiary.bean.ProvinceInfo;
import com.xiong.stockdiagnosticdiary.bean.XianMu;
import com.xiong.stockdiagnosticdiary.databinding.ActivityYuYueBinding;
import com.xiong.stockdiagnosticdiary.db.LearningDatabase;
import com.xiong.stockdiagnosticdiary.wwwwwww.HttpUtils;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.XToastUtils;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YuYueActivity extends AppCompatActivity {
    private List<ProvinceInfo> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private boolean xuan=false;
    private ActivityYuYueBinding binding;
    private Hairstylist yuanGonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        binding = ActivityYuYueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("预约");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.fhb);
        }
        binding.tvYg.setOnClickListener(v -> init());
        data();
        XianMu xianMuId1 = (XianMu) getIntent().getSerializableExtra("xianMuId");
        binding.textView4.setText(xianMuId1.getTitle());
        binding.textView5.setText("商品价格:"+xianMuId1.getPrice());
        binding.textView6.setText("会员特价:"+xianMuId1.getMember_price());
        int xianMuId =xianMuId1.getId();

        binding.btnYy.setOnClickListener(view -> {
//            Log.e(TAG, "onCreate: "+xianMuId );
//            Log.e(TAG, "onCreate: "+yuanGonName.getId() );
//            Log.e(TAG, "onCreate: "+xianMuId );
            Member member = LoginActivity.Global.INSTANCE.getMember();
            if (!xuan){
                Toast.makeText(YuYueActivity.this,"请选择店铺和员工",Toast.LENGTH_SHORT).show();

            }else {
                HttpUtils.yuYue(xianMuId+"", yuanGonName.getId()+"", yuanGonName.getShopId()+"",member.getId()+"", new HttpUtils.Callback() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "----+++----: "+response );
                        runOnUiThread(() -> {
                            if (response.equals("1")){
                                Toast.makeText(YuYueActivity.this,"预约成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(YuYueActivity.this,"预约失败",Toast.LENGTH_SHORT).show();
                            }
                        });


                    }

                    @Override
                    public void onFailure(IOException e) {
                        runOnUiThread(() -> Toast.makeText(YuYueActivity.this,"预约失败，清检查网络",Toast.LENGTH_SHORT).show());


                    }
                });

            }

           });
    }
        private void data(){
            LearningDatabase hairstylistDao = LearningDatabase.getLearningDatabase(this);
            List<String> allShopNames = hairstylistDao.hairstylistDao().getAllShopNames();
            for (int i = 0; i < allShopNames.size(); i++) {
                ArrayList<String> list  = new ArrayList<>();;
                List<Hairstylist> findShopName = hairstylistDao.hairstylistDao().findShopName(allShopNames.get(i));
                for (int n=0;n<findShopName.size();n++){
                    list.add(findShopName.get(n).getName());
                }
                ProvinceInfo provinceInfo = new ProvinceInfo();
                provinceInfo.setName(allShopNames.get(i));
                provinceInfo.setCityList(list);
                options1Items.add(provinceInfo);
                options2Items.add(list);
            }

        }
    private void init() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, (v, options1, options2, options3) -> {
            //返回的分别是三个级别的选中位置
            String tx = options1Items.get(options1).getPickerViewText() + "-" +
                    options2Items.get(options1).get(options2) ;

            XToastUtils.toast(tx);
            yuanGonName = LearningDatabase.getLearningDatabase(this).hairstylistDao().findYuanGonName(options2Items.get(options1).get(options2));
            Log.e(TAG, "init: "+yuanGonName.getShopId() );
            binding.tvYg.setText(yuanGonName.getShop().getName()+"-"+yuanGonName.getName());
            xuan=true;
            return false;
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                //切换选项时，还原到第一项
                .isRestoreItem(true)
                //设置选中项文字颜色
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .isDialog(false)
                .setSelectOptions(0, 1)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items);
        //pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}
