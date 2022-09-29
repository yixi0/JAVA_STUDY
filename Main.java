import java.util.*;

/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/9/21 16:51
 */

/**
 * 第一步是验证用户的身份（鉴别），第二步是验证用户的权限（授权）
 * <p>
 * 首先验证一个用户是否是该用户所声称的那个身份。例如，通过验证用户提供的口令（Password）是否正确，或者通过验证用户提供的智能卡是否合法有效。
 * <p>
 * 在授权的步骤中，权限策略会被检索以便判断来访的用户是否能够操作系统中的某个资源
 * <p>
 * 首先设定若干角色，每个角色中指明了一个清单，表明允许访问的资源的种类、资源的名称和对资源的操作；然后将被前一步骤已经鉴别过的用户和一个或多个角色相关联
 * <p>
 * 每个待授权的行为都会包含主体用户和其关联的用户组的信息
 */

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n, m, q;
        n = input.nextInt();
        m = input.nextInt();
        q = input.nextInt();
        Role[] roles = new Role[n];
        //设置角色
        for (int i = 0; i < n; i++) {
            //角色名字
            roles[i] = new Role();
            String roleName = input.next();
            //操作
            int nv = input.nextInt();
            List<String> toDoList = new ArrayList<>();
            for (int j = 0; j < nv; j++) {
                toDoList.add(input.next());
            }

            //资源种类
            int no = input.nextInt();
            List<String> resourceType = new ArrayList<>();
            for (int j = 0; j < no; j++) {
                resourceType.add(input.next());
            }

            //资源名称
            int nn = input.nextInt();
            List<String> resourceName = new ArrayList<>();
            for (int j = 0; j < nn; j++) {
                resourceName.add(input.next());
            }

            roles[i].ListRoleName(roleName);
            roles[i].ListToDoList(toDoList);
            roles[i].ListResourceType(resourceType);
            roles[i].ListResourceName(resourceName);
        }

        //角色关联
        for (int i = 0; i < m; i++) {
            //该角色在数组中的索引
            int idx = 0;
            String roleName = input.next();
            //查找到这个角色
            for (int j = 0; j < roles.length; j++) {
                if (Objects.equals(roleName, roles[j].getRoleName())) {
                    idx = j;
                    break;
                }
            }
            int ns = input.nextInt();
            for (int j = 0; j < ns; j++) {
                String type = input.next();
                String name = input.next();
                if (Objects.equals(type, "g")) {
                    if (roles[idx].getUserGroups() == null) {
                        roles[idx].ListUserGroups(new ArrayList<>());
                    }
                    List<String> userGroups = roles[idx].getUserGroups();
                    userGroups.add(name);
                    roles[idx].ListUserGroups(userGroups);
                } else if (Objects.equals(type, "u")) {
                    if (roles[idx].getUsers() == null) {
                        roles[idx].ListUsers(new ArrayList<>());
                    }
                    List<String> users = roles[idx].getUsers();
                    users.add(name);
                    roles[idx].ListUsers(users);
                }
            }
        }
        //待授权的行为
        User[] users = new User[q];
        for (int j = 0; j < q; j++) {
            users[j] = new User();
        }
        for (int j = 0; j < q; j++) {
            String userName = input.next();
            int ng = input.nextInt();
            List<String> userGroups = new ArrayList<>();
            for (int k = 0; k < ng; k++) {
                userGroups.add(input.next());
            }
            users[j].ListName(userName);
            users[j].ListUserGroup(userGroups);
            users[j].ListOperation(input.next());
            users[j].ListResourceType(input.next());
            users[j].ListResourceName(input.next());
            int hasAuthentication = users[j].hasAuthentication(roles);
            System.out.println(hasAuthentication);
        }
    }
}


//用户
class User {
    private String name;
    private List<String> userGroup = new ArrayList<>();

    private String operation;

    private String resourceType;

    private String resourceName;

    public User() {
    }

    public User(String name, List<String> userGroup) {
        this.name = name;
        this.userGroup = userGroup;
    }

    public String getName() {
        return name;
    }

    public void ListName(String name) {
        this.name = name;
    }

    public List<String> getUserGroup() {
        return userGroup;
    }

    public void ListUserGroup(List<String> userGroup) {
        this.userGroup = userGroup;
    }

    public String getOperation() {
        return operation;
    }

    public void ListOperation(String operation) {
        this.operation = operation;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void ListResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void ListResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer hasAuthentication(Role[] roles) {
        //roles[]绑定有用户和用户组
        //user设置有关联的用户组
        //遍历角色的用户组和用户名，如果存在，则代表该用户拥有此角色，查找此角色是否有
        //该操作，如果有，再看该角色是否有该资源类型，再看该角色是否有该资源名称

        boolean result = false;
        boolean hasAuthentication;
        boolean hasOperation;
        boolean hasResourceType;
        //遍历角色
        for (int i = 0; i < roles.length; i++) {
            if (result) {
                break;
            }
            result = false;
            hasAuthentication = false;
            hasOperation = false;
            hasResourceType = false;
            List<String> userGroups1 = roles[i].getUserGroups();
            List<String> userGroups2 = getUserGroup();
            List<String> users = roles[i].getUsers();

            //判断用户是否拥有该角色
            for (int j = 0; j < users.size(); j++) {
                if (Objects.equals(getName(), users.get(j))) {
                    hasAuthentication = true;
                    break;
                }
            }
            List<String> temp = new ArrayList<>();
            temp.addAll(userGroups1);
            temp.retainAll(userGroups2);
            if (temp.size() > 0) {
                hasAuthentication = true;
            }
            if (!hasAuthentication) {
                continue;
            }
            Role role = roles[i];
            //如果有角色，再继续判断是否有操作
            List<String> toDoList = role.getToDoList();
            if (toDoList.size() == 0 || Objects.equals(toDoList.get(0), "*")) {
                hasOperation = true;
            } else {
                for (String s : toDoList) {
                    if (Objects.equals(s, getOperation())) {
                        hasOperation = true;
                        break;
                    }
                }
            }
            //0 代表任意操作

            if (hasOperation) {
                List<String> resourceType = role.getResourceType();
                if (resourceType.size() == 0 || Objects.equals(resourceType.get(0), "*")) {
                    hasResourceType = true;
                } else {
                    for (String s : resourceType) {
                        if (Objects.equals(s, getResourceType())) {
                            hasResourceType = true;
                            break;
                        }
                    }
                }
            }
            if (hasResourceType) {
                List<String> resourceName = role.getResourceName();
                if (resourceName.size() == 0) {
                    result = true;
                } else {
                    for (String s : resourceName) {
                        if (Objects.equals(s, getResourceName())) {
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        if (result) {
            return 1;
        }
        return 0;
    }
}

class Role {
    private String roleName;
    private List<String> toDoList;

    private List<String> resourceName;

    private List<String> resourceType;

    private List<String> users;

    private List<String> userGroups;

    public Role() {
        users = new ArrayList<>();
        userGroups = new ArrayList<>();
    }

    public String getRoleName() {
        return roleName;
    }

    public void ListRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getToDoList() {
        return toDoList;
    }

    public void ListToDoList(List<String> toDoList) {
        this.toDoList = toDoList;
    }

    public List<String> getResourceName() {
        return resourceName;
    }

    public void ListResourceName(List<String> resourceName) {
        this.resourceName = resourceName;
    }

    public List<String> getResourceType() {
        return resourceType;
    }

    public void ListResourceType(List<String> resourceType) {
        this.resourceType = resourceType;
    }

    public List<String> getUsers() {
        return users;
    }

    public void ListUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getUserGroups() {
        return userGroups;
    }

    public void ListUserGroups(List<String> userGroups) {
        this.userGroups = userGroups;
    }

}
