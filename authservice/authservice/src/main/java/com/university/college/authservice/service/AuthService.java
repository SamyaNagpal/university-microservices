@Service
public class AuthService {

    private final AuthRepository _AuthRepository;
    private final PasswordEncoder _PasswordEncoder;    

    @Autowired
    AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder)
    {
        this._AuthRepository = authRepository;
        this._PasswordEncoder = passwordEncoder;
    }

    public boolean SignUp(SignupDto cred, ReturnDto response) 
    {
        // ✅ FIXED HERE
        cred.setPassword(_PasswordEncoder.encode(cred.getPassword()));

        StringBuffer status = new StringBuffer();
        boolean isSuccess = _AuthRepository.SignUp(cred, status);

        if (isSuccess) 
        {
            response.set_Status("User registration successful.");
        }
        else
        {
            response.set_Status("User registration failed: " + status.toString());
        }

        // ✅ FIXED HERE
        response.set_Email(cred.getEmail());
        return isSuccess;
    }

    public Boolean Authenticate(AuthDto cred) 
    {
        String email = cred.getEmail();
        String password = cred.getPassword();

        StringBuffer status = new StringBuffer();
        StringBuffer passwordFromDB = new StringBuffer();

        Boolean isSuccess = _AuthRepository.getPasswordFromEmail(email, passwordFromDB, status);

        if (isSuccess && _PasswordEncoder.matches(password, passwordFromDB.toString())) {
            return true;
        }

        return false;
    }
}
