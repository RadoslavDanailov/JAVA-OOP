package animals;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    protected Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
           if (name.trim().isEmpty()) {
               throw new IllegalArgumentException("Invalid input!");
           }
           this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
       this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid input!");
        }
       this.gender = gender;
    }
    protected abstract String produceSound();

    @Override
    public String toString() {
        return String.format("%s%n%s %d %s%n%s",
                this.getClass().getSimpleName(),
                this.getName(), this.getAge(), this.getGender(), this.produceSound());
    }




//private final String name;
//    private final int age;
//    private final String gender;
//
//    protected Animal(String name, int age, String gender) {
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//    }
//
//    public abstract String produceSound();
//
//    public String getName() {
//        return name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder(26);
//
//        sb.append(this.getClass().getSimpleName());
//        sb.append("\r");
//        sb.append("\n");
//
//        sb.append(name);
//        sb.append(' ');
//        sb.append(age);
//        sb.append(' ');
//        sb.append(gender);
//        sb.append("\r");
//        sb.append("\n");
//
//        sb.append(this.produceSound());
//
//        return sb.toString();
//    }


}
