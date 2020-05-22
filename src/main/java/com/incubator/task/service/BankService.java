package com.incubator.task.service;
import com.incubator.task.DAO.BankJdbc;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankService {

    BankJdbc bankJdbc = new BankJdbc();

    //Метод для нахождения наибогатейшего юзера
    public String getRichestUser() throws SQLException {

        // Получаем список всех юзеров
        ArrayList<ArrayList<Integer>> listAccs = bankJdbc.searchAllAccounts();
        ArrayList<Integer> richestUserAcc = new ArrayList();

        int count = 0;

        //Проходимся по списку и определяем богатейшего
        for (int i = 0; i < listAccs.size(); i++) {

            if (listAccs.get(i).get(1) > count) {

                count = listAccs.get(i).get(1);
                richestUserAcc = listAccs.get(i);
            }
        }
        return bankJdbc.searchUserById(richestUserAcc.get(2)).toString();
    }

    //Метод для нахождения общей суммы со всех аккаунтов
    public int accSum() throws SQLException {

        ArrayList<ArrayList<Integer>> listAccs = bankJdbc.searchAllAccounts();
        int sum = 0;

        for (int i = 0; i < listAccs.size(); i++) {
            sum += listAccs.get(i).get(1);
        }
        return sum;
    }
}
