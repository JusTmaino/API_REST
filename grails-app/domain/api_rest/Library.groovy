package api_rest


class Library {

    String name
    String address
    Integer yearCreated

    static hasMany = [books:Book]

    Library(String name , String address , Integer yearCreated){
        this.name = name
        this.address = address
        this.yearCreated = yearCreated
    }

    static constraints = {
        name     blank: false
        address  blank: false
        yearCreated nullable: false
    }
}
