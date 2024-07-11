import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// 定義產品類別和銷售稅率的類
public class TaxCalculator {
    // 定義各州的銷售稅率
    private static final Map<String, Double> stateTaxRates = new HashMap<>();
    static {
        stateTaxRates.put("CA", 0.0975); // California 銷售稅率為 9.75%
        stateTaxRates.put("NY", 0.08875); // New York 銷售稅率為 8.875%
    }

    // 定義各州的產品免稅類別
    private static final Map<String, List<String>> stateTaxExemptCategories = new HashMap<>();
    static {
        List<String> caExemptCategories = new ArrayList<>();
        caExemptCategories.add("food");
        stateTaxExemptCategories.put("CA", caExemptCategories);

        List<String> nyExemptCategories = new ArrayList<>();
        nyExemptCategories.add("food");
        nyExemptCategories.add("clothing");
        stateTaxExemptCategories.put("NY", nyExemptCategories);
    }

    // 取得指定州的銷售稅率
    public static double getTaxRate(String state) {
        return stateTaxRates.getOrDefault(state, 0.0);
    }

    // 檢查產品在指定州是否免稅
    public static boolean isTaxExempt(String state, String category) {
        List<String> exemptCategories = stateTaxExemptCategories.get(state);
        if (exemptCategories != null) {
            return exemptCategories.contains(category.toLowerCase());
        }
        return false;
    }

    // 對金額進行四捨五入到最近的0.05
    public static double roundToNearest0_05(double amount) {
        return Math.ceil(amount * 20) / 20.0;
    }
}
