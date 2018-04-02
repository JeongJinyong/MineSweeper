package com.example.jjy.minesweeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mine_grid)
    RecyclerView mineGrid;
    @BindView(R.id.mine_edit)
    EditText mineEdit;
    private String[][] mineList;
    private int mine_cnt = 10;
    private final String mine_str = "*";
    private final String null_str = "?";
    private ArrayList<MineClass> mineClasses = new ArrayList<>();
    private MineAdapter mineAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mineAdapter = new MineAdapter(this,mineClasses);
        mineGrid.setAdapter(mineAdapter);
        mineGrid.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        mineGrid.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        setMine(mine_cnt);
    }

    /**
     * 지뢰찾기 배열 초기화
     */
    private void settingMineList(){
        mineList = new String[10][10];
        for(int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                mineList[x][y] = null_str;
            }
        }
    }

    /**
     * 기본값 10개 / 사용자가 입력한 만큼 지뢰 넣기
     * @param mine_cnt
     */
    private void setMine(int mine_cnt){
        settingMineList();
        Random ran = new Random();
        for(int i = 0; i < mine_cnt; i++){
            int mineList_x = ran.nextInt(10);
            int mineList_y = ran.nextInt(10);
            if(mineList[mineList_x][mineList_y].equals(mine_str)){
                i--;
                continue;
            }
            if(mineList[mineList_x][mineList_y].equals(null_str))
                mineList[mineList_x][mineList_y] = mine_str;
        }
        setMineNumber();
    }

    /**
     * 해당 위치에 지뢰가 있는지 확인
     * @param x
     * @param y
     * @return isMine
     */
    private boolean isMine(int x, int y){
        if(x < 0 || x >= 10 || y < 0 || y >= 10){
            return false;
        }
        return mineList[x][y].equals(mine_str);
    }

    /**
     * 해당 위치 주위 8칸에 지뢰가 있는지 확인하고 있는만큼 카운트 하여 수를 넣어줌
     */
    private void setMineNumber(){
        mineClasses.clear();
        for(int x = 0; x < 10; x++){
            for(int y = 0;y < 10; y++){
                if(mineList[x][y].equals(null_str)){
                    int mine_cnt = 0;
                    if (isMine(x - 1, y - 1)) mine_cnt++;
                    if (isMine(x - 1, y)) mine_cnt++;
                    if (isMine(x - 1, y + 1)) mine_cnt++;
                    if (isMine(x, y - 1)) mine_cnt++;
                    if (isMine(x, y + 1)) mine_cnt++;
                    if (isMine(x + 1, y - 1)) mine_cnt++;
                    if (isMine(x + 1, y)) mine_cnt++;
                    if (isMine(x + 1, y + 1)) mine_cnt++;

                    if (mine_cnt != 0) mineList[x][y] = String.valueOf(mine_cnt);
                }
                mineClasses.add(new MineClass(mineList[x][y]));
            }
        }
        mineAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.restart_btn, R.id.answer_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.restart_btn:
                String cnt = mineEdit.getText().toString();
                if(!cnt.equals("")) mine_cnt = Integer.parseInt(cnt);
                else mine_cnt = 10;
                if(mine_cnt > 99) {
                    Toast.makeText(this, "지뢰의 개수가 99를 넘을 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                setMine(mine_cnt);
                break;
            case R.id.answer_btn:
                for (MineClass m : mineClasses) {
                    m.visibility = View.VISIBLE;
                }
                mineAdapter.notifyDataSetChanged();
                break;
        }
    }
}
