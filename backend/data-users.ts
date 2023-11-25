import { v4 as uuidv4 } from 'uuid';
import bcrypt from 'bcrypt';
async function hasher(password: string) {
	const salt = await bcrypt.genSalt(10);

	// Hash the password with the salt
	const hashedPassword = await bcrypt.hash(password, salt);
	return hashedPassword;
}


export const users = [
	{
		uid: uuidv4(),
		first_name: "Ulysses Cobb",
		last_name: "Carrillo",
		phone: "(542) 245-8037",
		email: "magna.nec.quam@yahoo.com",
		password: hasher("427277"),
		enableNotifs: true
	},
	{
		uid: uuidv4(),
		first_name: "Kenyon Osborne",
		last_name: "Ramos",
		phone: "(815) 204-8691",
		email: "in.lorem@outlook.org",
		password: hasher("073048"),
		enableNotifs: true
	},
	{
		uid: uuidv4(),
		first_name: "Kylie Payne",
		last_name: "Hendrix",
		phone: "1-379-271-6256",
		email: "pellentesque.sed.dictum@icloud.ca",
		password: hasher("066794"),
		enableNotifs: true
	},
	{
		uid: uuidv4(),
		first_name: "Inez Hester",
		last_name: "Gonzalez",
		phone: "1-341-611-8825",
		email: "quisque.varius@outlook.com",
		password: hasher("235854"),
		enableNotifs: true
	},
	{
		uid: uuidv4(),
		first_name: "Tatum Fry",
		last_name: "Hendricks",
		phone: "1-722-716-7362",
		email: "erat.vel.pede@google.edu",
		password: hasher("665655"),
		enableNotifs: true
	},
]