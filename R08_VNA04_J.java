// Rule 08. Visibility and Atomicity (VNA)
// VNA04-J. Ensure that calls to chained methods are atomic


final class USCurrency {
  // Change requested, denomination (optional fields)
  private int quarters = 0;
  private int dimes = 0;
  private int nickels = 0;
  private int pennies = 0;

  public USCurrency() {}

  // Setter methods
  public USCurrency setQuarters(int quantity) {
    quarters = quantity;
    return this;
  }
  public USCurrency setDimes(int quantity) {
    dimes = quantity;
    return this;
  }
  public USCurrency setNickels(int quantity) {
    nickels = quantity;
    return this;
  }
  public USCurrency setPennies(int quantity) {
    pennies = quantity;
    return this;
  }
}

// Client code:
class ExampleClientCode {

  private final USCurrency currency = new USCurrency();
  // ...

  public ExampleClientCode() {

    Thread t1 = new Thread(new Runnable() {
        @Override public void run() {
          currency.setQuarters(1).setDimes(1);
        }
    });
    t1.start();

    Thread t2 = new Thread(new Runnable() {
        @Override public void run() {
          currency.setQuarters(2).setDimes(2);
        }
    });
    t2.start();

    //...
  }
}



final class USCurrency {
  private final int quarters;
  private final int dimes;
  private final int nickels;
  private final int pennies;

  public USCurrency(Builder builder) {
    this.quarters = builder.quarters;
    this.dimes = builder.dimes;
    this.nickels = builder.nickels;
    this.pennies = builder.pennies;
  }

  // Static class member
  public static class Builder {
    private int quarters = 0;
    private int dimes = 0;
    private int nickels = 0;
    private int pennies = 0;

    public static Builder newInstance() {
      return new Builder();
    }

    private Builder() {}

    // Setter methods
    public Builder setQuarters(int quantity) {
      this.quarters = quantity;
      return this;
    }
    public Builder setDimes(int quantity) {
      this.dimes = quantity;
      return this;
    }
    public Builder setNickels(int quantity) {
      this.nickels = quantity;
      return this;
    }
    public Builder setPennies(int quantity) {
      this.pennies = quantity;
      return this;
    }

    public USCurrency build() {
      return new USCurrency(this);
    }
  }
}

// Client code:
class ExampleClientCode  {

  private volatile USCurrency currency;
  // ...

  public ExampleClientCode() {

    Thread t1 = new Thread(new Runnable() {
        @Override public void run() {
          currency = USCurrency.Builder.newInstance().
                         setQuarters(1).setDimes(1).build();
        }
    });
    t1.start();

    Thread t2 = new Thread(new Runnable() {
        @Override public void run() {
          currency = USCurrency.Builder.newInstance().
                         setQuarters(2).setDimes(2).build();
        }
    });
    t2.start();

    //...
  }
}

