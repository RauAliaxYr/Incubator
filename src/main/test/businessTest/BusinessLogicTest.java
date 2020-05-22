package businessTest;

import com.incubator.task.DAO.BankJdbc;
import com.incubator.task.service.BankService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

/*
* Я не особо разбираюсь в тестах
*/

public class BusinessLogicTest {

    BankService bankService = new BankService();
    BankJdbc bankJdbc = new BankJdbc();

    @Test
    public void testRich() throws SQLException {
        //тут пусто :(
    }

    @Test
    public void testAccSum() throws SQLException {

        int testMethodAccSum = bankService.accSum();

        ArrayList<ArrayList<Integer>> listAccs = null;
        try {
            listAccs = bankJdbc.searchAllAccounts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int sum = 0;

        for (int i = 0; i < listAccs.size(); i++) {
            sum += listAccs.get(i).get(1);
        }
        Assert.assertEquals(sum, testMethodAccSum);
    }
}
