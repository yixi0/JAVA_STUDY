import org.junit.Test;

import java.util.*;

class Catalogue {
    /**
     * type = 0 : dir
     * type = 1 : file
     */
    int type;
    long ld, lr;
    long ldSum, lrSum;
    HashMap<String, Catalogue> childCatalogue;

    Catalogue() {
        childCatalogue = new HashMap<>();
    }
}

public class Main {
    private static final String Y = "Y";
    private static final String N = "N";
    private static final String Q = "Q";
    private static final String R = "R";
    private static final String C = "C";

    private static Catalogue root = new Catalogue();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String filePath, operation;
        long fileSize, ld, lr;
        for (int i = 0; i < n; i++) {
            operation = input.next();
            filePath = input.next();
            if (Objects.equals(operation, C)) {
                fileSize = input.nextLong();
                System.out.println(executeC(filePath, fileSize));
            } else if (Objects.equals(operation, R)) {
                System.out.println(executeR(filePath));
            } else if (Objects.equals(operation, Q)) {
                ld = input.nextLong();
                lr = input.nextLong();
                System.out.println(executeQ(filePath, ld, lr));
            }
        }
    }

    @Test
    public void test() {
        String path = "/A/B/C";
        String[] split = path.split("/");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }

    public static String executeC(String filePath, long fileSize) {
        //判断是否能创建
        boolean isSuccessCreate = true;
        //判断文件额度是否满
        boolean isNotFull = true;

        String[] splitedFilePath = filePath.split("/");
        int fileNum = splitedFilePath.length;

        List<Catalogue> catalogues = new ArrayList<>();
        //占位子
        catalogues.add(null);
        Catalogue temp = root;
        int idx2 = 1;

        while (temp != null) {
            catalogues.add(temp);
            temp = temp.childCatalogue.get(splitedFilePath[idx2++]);
        }

        System.out.println(catalogues.size());

        //最后一级是目录或前面是文件，则创建失败
        for (int i = 1; i < catalogues.size(); i++) {
            Catalogue tempCatalogue = catalogues.get(i);
            if (tempCatalogue == null) {
                break;
            } else {
                if (i != fileNum - 1) {
                    if (tempCatalogue.type == 1) {
                        isSuccessCreate = false;
                        break;
                    }
                } else {
                    if (tempCatalogue.type == 0) {
                        isSuccessCreate = false;
                        break;
                    }
                }
            }
        }
        if (!isSuccessCreate) {
            return N;
        }
        //判断文件配额是否已满
        for (int i = 1; i < catalogues.size(); i++) {
            Catalogue catalogue = catalogues.get(i);
            //后代配额
            if (!(catalogue.lr == 0
                    || catalogue.lrSum + fileSize <= catalogue.lr)) {
                isNotFull = false;
                break;
            }
            //目录配额（倒数第二级）
            if (i == fileNum - 2) {
                if (!(catalogue.ld == 0
                        || catalogue.ldSum + fileSize
                        <= catalogue.ld)) {
                    isNotFull = false;
                    break;
                }
            }
        }

        if (!isNotFull) {
            return N;
        }

        //创建目录
        for (int i = catalogues.size(); i < fileNum - 1; i++) {
            Catalogue catalogue = catalogues.get(i - 1);
            Catalogue addCatalogue = new Catalogue();
            addCatalogue.lrSum = fileSize;
            if (i == fileNum - 2) {
                addCatalogue.ldSum = fileSize;
            }
            catalogue.childCatalogue.put(splitedFilePath[catalogues.size()], addCatalogue);
            catalogues.add(addCatalogue);
        }

        //创建文件
        Catalogue catalogue = catalogues.get(fileNum - 2);
        Catalogue addCatalogue = new Catalogue();
        addCatalogue.type = 1;
        catalogue.childCatalogue.put(splitedFilePath[catalogues.size()], addCatalogue);


        return Y;

    }

    public static String executeR(String filePath) {
        String[] splitedFilePath = filePath.split("/");
        int idx = 1;
        Catalogue temp = root;
        while (temp != null) {
            if (idx == splitedFilePath.length - 1) {
                break;
            }
            temp = temp.childCatalogue.get(splitedFilePath[idx++]);
        }
        if (idx < splitedFilePath.length - 1 || temp == null) {
            return Y;
        }

        temp.childCatalogue.remove(splitedFilePath[idx]);
        return Y;

    }

    public static String executeQ(String filePath, long ld, long lr) {
        String[] splitedFilePath = filePath.split("/");
        int idx = 1;
        Catalogue temp = root;
        while (temp != null) {
            if (idx == splitedFilePath.length - 1) {
                break;
            }
            temp = temp.childCatalogue.get(splitedFilePath[idx++]);
        }

        if (idx < splitedFilePath.length - 1 || temp == null) {
            return N;
        }
        if ((ld != 0 && ld < temp.ldSum) || (lr != 0 && lr < temp.lrSum)) {
            return N;
        }
        temp.ld = ld;
        temp.lr = lr;
        return null;
    }
}