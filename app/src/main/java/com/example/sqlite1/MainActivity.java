package com.example.sqlite1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TenHelper tenHelper;
    ListView lv;
    ArrayList<TenSV> arrayList;
    TenSV tenSV;

    TenAdapter adapter;
    EditText edtTen;
    Button btnThem, btnXoa, btnCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtTen.setText(arrayList.get(i).getTenSV());
                int position = i;

            }
        });

        btnCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTen.setText("");
            }
        });





        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bai = edtTen.getText().toString().trim();

                if (TextUtils.isEmpty(bai)){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                    return;
                }
                tenHelper.QueryData("INSERT INTO TenSV VALUES (null, '"+bai+"')");
                actionGetData();

            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bai = edtTen.getText().toString();
                if(TextUtils.isEmpty(bai)){
                    Toast.makeText(MainActivity.this, "Vui lòng chọn dữ liệu muốn xóa", Toast.LENGTH_SHORT).show();
                    return;
                }
                tenHelper.QueryData("delete from TenSV where TenSV =  '"+bai+"'");
                actionGetData();

            }
        });




//        btnXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String bai = edtTen.getText().toString().trim();
//
//                if(TextUtils.isEmpty(bai)){
//                    Toast.makeText(MainActivity.this, "Vui lòng chọn tên cần xóa", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                tenHelper.QueryData("DELETE FROM TenSV WHERE Id = ");
//                actionGetData();
//            }
//        });



        lv = (ListView) findViewById(R.id.lvNoiDung);
        edtTen  = (EditText) findViewById(R.id.edtTen);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnXoa = (Button) findViewById(R.id.button2);
        arrayList = new ArrayList<>();
        adapter = new TenAdapter(this, R.layout.dong_ten, arrayList);
        lv.setAdapter(adapter);
        //tạo database
        tenHelper = new TenHelper(this, "TenSinhVien.sqlite", null, 1);
        //tạo bảng
        tenHelper.QueryData("CREATE TABLE IF NOT EXISTS TenSV(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenSV VARCHAR(200))");
        //Thêm dữ liệu
//             tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Đỗ Anh Bô')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Hoàng Quốc Cường')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Phạm Minh Dũng')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Châu Hoàng Duy')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Trần Nhật Duy')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Nguyễn Đình Hảo')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Hà Khã Huê')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Nguyễn Hoàng Hữu')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Lê Nguyễn Quang Linh')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Nguyễn Công Minh')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Nguyễn Hoàng Nghĩa')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Trần Thanh Nhã')");
//        tenHelper.QueryData("INSERT INTO TenSV VALUES (null, 'Trương Hoàng Anh Vũ')");

//
        //Hiển thị
        Cursor dataTen = tenHelper.GetData("SELECT * FROM TenSV");
        while (dataTen.moveToNext()) {
            String ten = dataTen.getString(1);
            int id  = dataTen.getInt(0);
            //Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
            arrayList.add(new TenSV(id, ten));
        }
        adapter.notifyDataSetChanged();



        }

    private void actionGetData() {


        Cursor dataTen = tenHelper.GetData("SELECT * FROM TenSV");
        arrayList.clear();
        while (dataTen.moveToNext()) {
            String ten = dataTen.getString(1);
            int id  = dataTen.getInt(0);
            //Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
            arrayList.add(new TenSV(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    private void anhXa() {
        lv = (ListView) findViewById(R.id.lvNoiDung);
        edtTen  = (EditText) findViewById(R.id.edtTen);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnXoa = (Button) findViewById(R.id.button2);
        btnCC = (Button) findViewById(R.id.button3);

    }
    public  boolean delete(TenSV ten){
        try{
            tenHelper.QueryData("delete from TenSV where TenSV = " +tenSV.getTenSV());
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }



}
