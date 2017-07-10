package huge.itm.huge;

import android.graphics.drawable.Drawable;

/**
 * Created by 김준성 on 2016-09-24.
 */
public class ChildListData {
    /* CustomListView가 담을 객체에 대한 Class 생성 */
    // ImageView에 상응
    public Drawable mChildItem;
    // TextView01에 상응
    public String mChildText;
    // TextView02에 상응
    public String mTest02;
    public ChildListData(Drawable d, String s) {
        mChildItem=d;
        mChildText=s;
    }

}