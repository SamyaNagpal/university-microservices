@Repository(value = "AuthRepository")
public class AuthRepository {

  JdbcTemplate _JdbcTemplate;

  @Autowired
  AuthRepository(JdbcTemplate jdbcTemplate) {
    this._JdbcTemplate = jdbcTemplate;
  }

  public boolean SignUp(SignupDto cred, StringBuffer error) {
    try {
      String query = "insert into users (u_name, u_email, u_password) values (?, ?, ?)";

      // ✅ FIXED ALL 3 HERE
      _JdbcTemplate.update(
          query,
          cred.getName(),
          cred.getEmail(),
          cred.getPassword());

    } catch (Exception ex) {
      error.append(ex.getMessage());
      System.out.println("Error during user signup: " + error);
      return false;
    }

    return true;
  }

  public Boolean getPasswordFromEmail(String email, StringBuffer password, StringBuffer error) {
    try {
      String query = "select u_password from users where u_email = ?";
      password.append(_JdbcTemplate.queryForObject(query, String.class, email));

    } catch (Exception ex) {
      error.append(ex.getMessage());
      System.out.println("Error during user authentication: " + error);
      return false;
    }

    return true;
  }
}
