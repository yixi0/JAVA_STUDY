import java.util.Objects;
import java.util.Scanner;

/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/10/20 14:09
 */
public class CSP201703_2 {

    private static final int SERVANT_SUM = 7;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        //0为先手，1为后手
        int idx = 0;
        //先手
        Hero h1 = new Hero();
        //后手
        Hero h2 = new Hero();
        Hero[] heroes = new Hero[2];
        heroes[0] = h1;
        heroes[1] = h2;
        String operation;

        for (int i = 0; i < n; i++) {
            operation = input.next();
            Hero thisTurn = heroes[idx];
            if (Objects.equals(operation, "end")) {
                idx = (idx + 1) % 2;
            } else if (Objects.equals(operation, "summon")) {
                int position = input.nextInt();
                int attack = input.nextInt();
                int health = input.nextInt();
                if (thisTurn.servants[position - 1].defence != 0) {
                    //该位置有随从
                    int lastLen = 0;
                    for (int j = position; j < SERVANT_SUM; j++) {
                        if (thisTurn.servants[j].defence == 0) {
                            lastLen = j;
                            break;
                        }
                    }
                    for (int j = lastLen - 1; j >= position - 1; j--) {
                        thisTurn.servants[j + 1] = thisTurn.servants[j];
                    }
                }
                thisTurn.servants[position - 1] = new Servant(attack, health);
            } else if (Objects.equals(operation, "attack")) {
                int attacker = input.nextInt();
                int defender = input.nextInt();

                Servant attackerServant = thisTurn.servants[attacker - 1];
                if (defender == 0) {
                    //如果防守方是英雄
                    heroes[(idx + 1) % 2].defence -= attackerServant.attack;
                } else {
                    Servant defendServant = heroes[(idx + 1) % 2].servants[defender - 1];
                    defendServant.defence -= attackerServant.attack;
                    attackerServant.defence -= defendServant.attack;
                    //判断是否有随从死亡
                    //防守方
                    if (defendServant.defence <= 0) {
                        Servant[] defendServants = heroes[(idx + 1) % 2].servants;
                        removeDeath(defendServants, defender);
                    }
                    //进攻方
                    if (attackerServant.defence <= 0) {
                        Servant[] attackServants = heroes[idx].servants;
                        removeDeath(attackServants, attacker);
                    }
                }
            }
        }

        if (heroes[0].defence > 0 && heroes[1].defence > 0) {
            System.out.println(0);
        } else if (heroes[0].defence > 0) {
            System.out.println(1);
        } else if (heroes[1].defence > 0) {
            System.out.println(-1);
        }

        System.out.println(heroes[0].defence);
        Servant[] s1 = heroes[0].servants;
        print(s1);

        System.out.println(heroes[1].defence);
        Servant[] s2 = heroes[1].servants;
        print(s2);


    }

    public static void removeDeath(Servant[] servants, int idx) {
        int lastLen = 0;
        for (int j = idx; j < SERVANT_SUM; j++) {
            if (servants[j].defence== 0) {
                lastLen = j;
                break;
            }
        }
        for (int j = idx; j < lastLen + 1; j++) {
            servants[j - 1] = servants[j];
        }
    }

    private static void print(Servant[] s) {

        int sLen = 0;
        for (int i = 0; i < SERVANT_SUM; i++) {
            if (s[i].defence == 0) {
                break;
            }
            sLen++;
        }
        System.out.print(sLen + " ");
        for (int i = 0; i < sLen; i++) {
            System.out.print(s[i].defence + " ");
        }
        System.out.println();
    }
}


class Hero {
    int defence;
    Servant[] servants;

    public Hero() {
        defence = 30;
        servants = new Servant[7];
        for (int i = 0; i < servants.length; i++) {
            servants[i] = new Servant();
        }
    }
}

class Servant {
    int attack;
    int defence;

    public Servant() {
    }

    public Servant(int attack, int defence) {
        this.attack = attack;
        this.defence = defence;
    }
}
