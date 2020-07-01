package dominio;

public class Cliente 
{
	private int _idCliente;
	private String _nombre;
	private String _apellido;
	private String _email;
	private String _telefono;
	private double _saldo;
	
	public Cliente() 
	{
		
	}
	
	public Cliente(int idCliente) 
	{
		_idCliente = idCliente;
	}
	

	public Cliente(String nombre, String apellido, String email, String telefono, double saldo) 
	{
		_nombre = nombre;
		_apellido = apellido;
		_email = email;
		_telefono = telefono;
		_saldo = saldo;
	}
	
	public Cliente(int idCliente, String nombre, String apellido, String email, String telefono, double saldo) 
	{
		_idCliente = idCliente;
		_nombre = nombre;
		_apellido = apellido;
		_email = email;
		_telefono = telefono;
		_saldo = saldo;
	}

	public int getIdCliente() 
	{
		return _idCliente;
	}

	public void setIdCliente(int idCliente) 
	{
		_idCliente = idCliente;
	}

	public String getNombre() 
	{
		return _nombre;
	}

	public void setNombre(String nombre) 
	{
		_nombre = nombre;
	}

	public String getApellido() 
	{
		return _apellido;
	}

	public void setApellido(String apellido) 
	{
		_apellido = apellido;
	}
	
	public String getEmail() 
	{
		return _email;
	}

	public void setEmail(String email) 
	{
		_email = email;
	}

	public String getTelefono() 
	{
		return _telefono;
	}

	public void setTelefono(String telefono) 
	{
		_telefono = telefono;
	}

	public double getSaldo() 
	{
		return _saldo;
	}

	public void setSaldo(double saldo) 
	{
		_saldo = saldo;
	}

	@Override
	public String toString() 
	{
		return "Cliente [_idCliente=" + _idCliente + ", _nombre=" + _nombre + ", _apellido=" + _apellido
				+ ", _telefono=" + _telefono + ", _saldo=" + _saldo + "]";
	}
	
	
	
}
