package by.epam.java_training.mihail_poliansky.final_project.dao.constant;

public interface SqlQueries {

    String INSERT_USER = "INSERT INTO users (usr_nickname, role_id, usr_id) VALUES (?, ?, ?)";
    String UPDATE_USER = "UPDATE users SET usr_nickname = ?, role_id = ? WHERE usr_id = ?;";
    String DELETE_USER = "DELETE FROM users WHERE usr_id = ?;";
    String SELECT_USER = "SELECT * FROM users WHERE usr_id = ?;";

    String INSERT_USER_PRIVATES = "INSERT INTO user_privates (mail, password) VALUES (?, ?);";
    String UPDATE_USER_PRIVATES = "UPDATE user_privates SET mail = ?, password = ? WHERE usr_id = ?;";
    String DELETE_USER_PRIVATES = "DELETE FROM user_privates WHERE usr_id = ?;";
    String SELECT_USER_PRIVATES = "SELECT * FROM user_privates WHERE usr_id = ?;";

    String INSERT_CASH_FLOW = "INSERT INTO cash_flow (usr_id, date, cost, cfi_id) VALUES (?, ?, ?, ?);";
    String DELETE_CASH_FLOW = "DELETE FROM cash_flow WHERE usr_id = ? AND date = ? AND cf_id = ?;";
    String UPDATE_CASH_FLOW = "UPDATE cash_flow SET cost = ?, cfi_id = ? WHERE cf_id = ?;";
    String SELECT_CASH_FLOW = "SELECT * FROM cash_flow INNER JOIN cash_flow_items using(cfi_id) WHERE usr_id = ? AND date = ?;";
    String SELECT_ALL_CASH_FLOW = "SELECT * FROM cash_flow INNER JOIN cash_flow_items using(cfi_id) WHERE usr_id = ? AND cfi_id = ?;";

    String SELECT_CASH_FLOW_ITEMS = "SELECT * FROM users_has_cash_flow_items INNER JOIN cash_flow_items i on users_has_cash_flow_items.cfi_id = i.cfi_id WHERE usr_id = ?;";
    String INSERT_CASH_FLOW_ITEMS = "INSERT INTO cash_flow_items (item_name) VALUES (?);";
    String DELETE_CASH_FLOW_ITEMS = "DELETE FROM cash_flow_items WHERE cfi_id = ?;";
    String UPDATE_CASH_FLOW_ITEMS = "UPDATE cash_flow_items SET item_name = ? WHERE cfi_id = ?;";

    String SELECT_TIME_MANAGER_ITEMS = "SELECT * FROM time_manage_items INNER JOIN users_has_time_manage_items m on time_manage_items.tmi_id = m.tmi_id WHERE usr_id = ?;";
    String INSERT_TIME_MANAGE_ITEMS = "INSERT INTO time_manage_items (item_name) VALUES (?);";
    String UPDATE_TIME_MANAGE_ITEMS = "UPDATE time_manage_items SET item_name = ? WHERE tmi_id = ?;";
    String DELETE_TIME_MANAGE_ITEMS = "DELETE FROM time_manage_items WHERE tmi_id = ?;";

    String UPDATE_TIME_MANAGE = "UPDATE time_manage SET time_begin = ?, time_end = ?, tmi_id = ? WHERE tm_id = ?;";
    String DELETE_TIME_MANAGE = "DELETE FROM time_manage WHERE usr_id = ? AND date = ? OR tm_id = ?;";
    String INSERT_TIME_MANAGE = "INSERT INTO time_manage (usr_id, date, time_begin, time_end, tmi_id) VALUES (?, ?, ?, ?, ?);";
    String SELECT_TIME_MANAGE = "SELECT * FROM time_manage INNER JOIN time_manage_items using(tmi_id) WHERE usr_id = ? AND date = ?;";
    String SELECT_ALL_TIME_MANAGE = "SELECT * FROM time_manage INNER JOIN time_manage_items using(tmi_id) WHERE usr_id = ? AND tmi_id = ?;";

    String FIND_USER_PRIVATES = "SELECT usr_id FROM user_privates WHERE password = ? AND mail = ?;";
    String FIND_CASH_FLOW_ITEM = "SELECT * FROM cash_flow_items WHERE item_name = ?;";
    String ADD_TO_USER_CFI = "INSERT INTO users_has_cash_flow_items (usr_id, cfi_id) VALUES (?, ?);";
    String DELETE_FROM_USER_CFI = "DELETE FROM users_has_cash_flow_items WHERE usr_id = ? AND cfi_id = ?;";

    String FIND_TIME_MANAGER_ITEM = "SELECT * FROM time_manage_items WHERE item_name = ?;";
    String ADD_TO_USER_TMI = "INSERT INTO users_has_time_manage_items (usr_id, tmi_id) VALUES (?, ?);";
    String DELETE_FROM_USER_TMI = "DELETE FROM users_has_time_manage_items WHERE usr_id = ? AND tmi_id = ?;";
    String CHECK_MAIL_IS_VACANT = "SELECT * FROM user_privates WHERE mail = ?;";
    String SELECT_ALL_ACTIVITIES_USER_HAS = "SELECT * FROM activities INNER JOIN users_has_activities m on activities.act_id = m.act_id WHERE usr_id = ?;";
    String ADD_ACTIVITY_TO_USER = "INSERT INTO users_has_activities (usr_id, act_id) VALUES (?, ?)";
    String SELECT_ALL_USERS = "SELECT * FROM users WHERE role_id = '1'";
    String SELECT_ALL_ACTIVITIES = "SELECT * FROM activities";
    String DELETE_ACTIVITY_FROM_USER = "DELETE FROM users_has_activities WHERE usr_id = ? AND act_id = ?";
}
