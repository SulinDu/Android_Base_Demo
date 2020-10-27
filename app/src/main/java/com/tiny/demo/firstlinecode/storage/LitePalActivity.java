package com.tiny.demo.firstlinecode.storage;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.storage.litepal.Book;
import com.tinytongtong.tinyutils.LogUtils;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LitePalActivity extends BaseActivity {

    @BindView(R.id.btn_litepal_create)
    Button btnLitepalCreate;
    @BindView(R.id.btn_litepal_add)
    Button btnLitepalAdd;
    @BindView(R.id.btn_litepal_update)
    Button btnLitepalUpdageDefault;
    @BindView(R.id.btn_litepal_set_default)
    Button btnLitepalUpdage;
    @BindView(R.id.btn_litepal_delete)
    Button btnLitepalDelete;
    @BindView(R.id.btn_litepal_query)
    Button btnLitepalQuery;
    @BindView(R.id.activity_litepal)
    LinearLayout activityLitepal;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_lite_pal;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_litepal_create, R.id.btn_litepal_add, R.id.btn_litepal_set_default,
            R.id.btn_litepal_update, R.id.btn_litepal_delete, R.id.btn_litepal_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_litepal_create:
                SQLiteDatabase db = LitePal.getDatabase();
                break;
            case R.id.btn_litepal_add:
                for (int i = 0; i < 20; i++) {
                    Book book = new Book();
                    book.setName("Tiny Book " + i);
                    book.setAuthor("tiny" + i);
                    book.setPages(500 + i);
                    book.setPrice(99.99d + i);
                    book.setPress("Unknow " + i);
                    book.save();
                }
                break;
            case R.id.btn_litepal_update:
                //使用litepal更新数据
                Book book = new Book();
                book.setName("tongtong");
                book.updateAll("pages = ?", "500");
                break;
            case R.id.btn_litepal_set_default:
                //使用litepal设置默认数据
                Book bookDefault = new Book();
                bookDefault.setToDefault("pages");
                bookDefault.updateAll("pages = ?", "501");
                break;

            case R.id.btn_litepal_delete:
                LitePal.deleteAll(Book.class, "pages > ?", "508");
                break;
            case R.id.btn_litepal_query:
                List<Book> booksAll = LitePal.findAll(Book.class);
                LogUtils.INSTANCE.e("所有数据：" + booksAll.toString());

                Book bookFirst = LitePal.findFirst(Book.class);
                LogUtils.INSTANCE.e("第一条数据：" + bookFirst.toString());

                Book bookLast = LitePal.findLast(Book.class);
                LogUtils.INSTANCE.e("最后一条数据：" + bookLast.toString());

                //查询某几列的数据：
                List<Book> books1 = LitePal.select("name", "author").find(Book.class);
                LogUtils.INSTANCE.e("name和author列的数据：" + books1.toString());

                //使用约束条件进行查询
                List<Book> books2 = LitePal.where("pages < ?", "507").find(Book.class);
                LogUtils.INSTANCE.e("页码小于507的数据：" + books2.toString());

                //给查询结果进行排序
                List<Book> books3 = LitePal.order("price desc").find(Book.class);
                LogUtils.INSTANCE.e("将查询结果按照价格降序排列：" + books3.toString());

                //制定查询结果的数量
                List<Book> books4 = LitePal.limit(3).find(Book.class);
                LogUtils.INSTANCE.e("查询前三项数据：" + books4.toString());

                //限制查询结果的偏移量和个数
                List<Book> books5 = LitePal.limit(3).offset(1).find(Book.class);
                LogUtils.INSTANCE.e("查询第二、三、四个数据的值：" + books5.toString());

                //链式调用
                List<Book> books6 = LitePal.select("name", "author", "pages")
                        .where("pages>=502")
                        .order("pages")
                        .limit(5)
                        .offset(2)
                        .find(Book.class);
                LogUtils.INSTANCE.e("链式调用：" + books6.toString());
                break;
            case R.id.btn_sql_add:
                break;
            case R.id.btn_sql_updage:
                break;
            case R.id.btn_sql_delete:
                break;
            case R.id.btn_sql_query:
                break;
            case R.id.activity_litepal:
                break;
        }
    }
}
