package net.jo.ggusers;

import java.util.List;

public class Presenter {

    private IView view;
    private Model model;

    public Presenter(IView view) {
        this.view = view;
        model = new Model();
    }

    public void showLoading() {
        view.showDialog();
    }


    void getData() {

        model.getData(new ICallback<List<UserBean>>() {
            @Override
            public void onSuccess(List<UserBean> data) {
                view.hideDialog();

                view.showList(data);
            }

            @Override
            public void onError(String msg) {
                view.showToast(msg);
            }

            @Override
            public void onFailure(String msg) {
                view.showToast(msg);
            }
        });
    }

    void getSingleUser(String login) {
        model.getSingleUser(login, new ICallback<UserName>() {
            @Override
            public void onSuccess(UserName data) {
                if (view != null) {

                    view.hideDialog();

                    view.showUser(data);
                }
            }

            @Override
            public void onError(String msg) {
                if (view != null)
                    view.showToast(msg);
            }

            @Override
            public void onFailure(String msg) {
                if (view != null)
                    view.showToast(msg);
            }
        });
    }
}
