public class Member {
    private String id_member;
    private String name;
    private String address;

    public Member(String id_member, String name, String address) {
        this.id_member = id_member;
        this.name = name;
        this.address = address;
    }

    public String getMemberId() {
        return id_member;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
