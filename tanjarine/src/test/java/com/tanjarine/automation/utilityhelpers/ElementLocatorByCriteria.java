package com.tanjarine.automation.utilityhelpers;

import com.tanjarine.automation.pageobjects.VenueManagementPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by ypolshchykau on 12.01.2015.
 */
public class ElementLocatorByCriteria {

    /**
     * method returning index of element in table of elements being search by string parameter 'pattern'
     *
     * @param elemenentsTable
     * @param pattern
     * @return
     * @throws InterruptedException
     */
    public static int getTableElementIndexByString(List<WebElement> elemenentsTable, String pattern) {
        int result = -1;
        for (int count = 0; count < elemenentsTable.size(); count++) {
            // get text of the element being analyzed
            String textCurrentElement = elemenentsTable.get(count).getText().toLowerCase().trim();
            if (textCurrentElement.contains(pattern)) {
                if (textCurrentElement.contains(pattern)) {
                    result = count;
                    return result;
                }
            }
        }
        return result;
    }


    public static int getElementToDelete(VenueManagementPage venues) {
        int thisindexOfElementToDelete = getTableElementIndexByString(venues.getChainsTable(), "automation");
        Assert.assertTrue(thisindexOfElementToDelete >= 0, "no chain starting with 'Automation' prefix");
        return thisindexOfElementToDelete;
    }

}
