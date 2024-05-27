package repository;

public class RepoAgent {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/PAO";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "ihtis-aei";

    private static final String ADD_AGENT_SQL  = "UPDATE AGENT SET agentie = ? WHERE id = ? ";

    private static final String DEL_AGENT_SQL  = "DELETE FROM AGENT WHERE nume = ?";


}
