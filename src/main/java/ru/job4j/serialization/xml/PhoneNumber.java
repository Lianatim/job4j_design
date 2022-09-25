package ru.job4j.serialization.xml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "phoneNumber")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumber {

    @XmlAttribute
    private boolean isRobot;
    @XmlAttribute
    private int phoneNumber;
    @XmlAttribute
    private String name;
    @XmlElement(name = "people")
    private People people;
    @XmlElementWrapper(name = "numbers")
    @XmlElement(name = "number")
    private String[] numbers;

    public PhoneNumber(boolean isRobot, int phoneNumber, String name, People people, String[] numbers) {
        this.isRobot = isRobot;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.people = people;
        this.numbers = numbers;
    }

    public PhoneNumber() {
    }

    @Override
    public String toString() {
        return "PhoneNumber{"
                + "isRobot=" + isRobot
                + ", phoneNumber=" + phoneNumber
                + ", name='" + name + '\''
                + ", people=" + people
                + ", numbers=" + Arrays.toString(numbers)
                + '}';
    }

    @XmlRootElement(name = "people")
    static class People {
        @XmlAttribute
        private int passportNumber;

        public People(int passportNumber) {
            this.passportNumber = passportNumber;
        }

        public People() {
        }

        @Override
        public String toString() {
            return "People{"
                    + "passportNumber=" + passportNumber
                    + '}';
        }
    }

    public static void main(String[] args) throws JAXBException, IOException {
        final PhoneNumber phoneNumber = new PhoneNumber(false, 555355, "Mike",
                new People(51239002), new String[]{"1, 2"});

        JAXBContext context = JAXBContext.newInstance(PhoneNumber.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(phoneNumber, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            PhoneNumber result = (PhoneNumber) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
