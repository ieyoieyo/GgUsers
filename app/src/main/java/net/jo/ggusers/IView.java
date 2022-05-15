package net.jo.ggusers;

import java.util.List;

public interface IView {

    void showDialog();
    void hideDialog();

    void showList(List<UserBean> data);
    void showUser(UserName data);

    void showToast(String msg);
}
