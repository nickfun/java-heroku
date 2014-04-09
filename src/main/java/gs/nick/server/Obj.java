package gs.nick.server;


class Obj {

    private String name;
    private int age;

    public void setName(String n) {
	name = n;
    }

    public String getName() {
	//System.out.println("Got name!");
	return name;
    }

    public void setAge(int a) {
	age = a;
    }

    public int getAge() {
	return age;
    }

}
