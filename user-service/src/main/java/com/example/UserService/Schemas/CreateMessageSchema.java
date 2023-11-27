/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example.UserService.Schemas;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class CreateMessageSchema extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -9222232954717447516L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CreateMessageSchema\",\"namespace\":\"com.example.UserService.Schemas\",\"fields\":[{\"name\":\"user_email\",\"type\":\"string\",\"doc\":\"User e-mail\"},{\"name\":\"message_type\",\"type\":\"int\",\"doc\":\"Type of message\"},{\"name\":\"delivery_method\",\"type\":\"int\",\"doc\":\"Delivery method (notification, e-mail, sms...)\"}],\"version\":\"1\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<CreateMessageSchema> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CreateMessageSchema> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CreateMessageSchema> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CreateMessageSchema> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CreateMessageSchema> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CreateMessageSchema to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CreateMessageSchema from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CreateMessageSchema instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CreateMessageSchema fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** User e-mail */
  private java.lang.CharSequence user_email;
  /** Type of message */
  private int message_type;
  /** Delivery method (notification, e-mail, sms...) */
  private int delivery_method;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CreateMessageSchema() {}

  /**
   * All-args constructor.
   * @param user_email User e-mail
   * @param message_type Type of message
   * @param delivery_method Delivery method (notification, e-mail, sms...)
   */
  public CreateMessageSchema(java.lang.CharSequence user_email, java.lang.Integer message_type, java.lang.Integer delivery_method) {
    this.user_email = user_email;
    this.message_type = message_type;
    this.delivery_method = delivery_method;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return user_email;
    case 1: return message_type;
    case 2: return delivery_method;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: user_email = (java.lang.CharSequence)value$; break;
    case 1: message_type = (java.lang.Integer)value$; break;
    case 2: delivery_method = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'user_email' field.
   * @return User e-mail
   */
  public java.lang.CharSequence getUserEmail() {
    return user_email;
  }


  /**
   * Sets the value of the 'user_email' field.
   * User e-mail
   * @param value the value to set.
   */
  public void setUserEmail(java.lang.CharSequence value) {
    this.user_email = value;
  }

  /**
   * Gets the value of the 'message_type' field.
   * @return Type of message
   */
  public int getMessageType() {
    return message_type;
  }


  /**
   * Sets the value of the 'message_type' field.
   * Type of message
   * @param value the value to set.
   */
  public void setMessageType(int value) {
    this.message_type = value;
  }

  /**
   * Gets the value of the 'delivery_method' field.
   * @return Delivery method (notification, e-mail, sms...)
   */
  public int getDeliveryMethod() {
    return delivery_method;
  }


  /**
   * Sets the value of the 'delivery_method' field.
   * Delivery method (notification, e-mail, sms...)
   * @param value the value to set.
   */
  public void setDeliveryMethod(int value) {
    this.delivery_method = value;
  }

  /**
   * Creates a new CreateMessageSchema RecordBuilder.
   * @return A new CreateMessageSchema RecordBuilder
   */
  public static com.example.UserService.Schemas.CreateMessageSchema.Builder newBuilder() {
    return new com.example.UserService.Schemas.CreateMessageSchema.Builder();
  }

  /**
   * Creates a new CreateMessageSchema RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CreateMessageSchema RecordBuilder
   */
  public static com.example.UserService.Schemas.CreateMessageSchema.Builder newBuilder(com.example.UserService.Schemas.CreateMessageSchema.Builder other) {
    if (other == null) {
      return new com.example.UserService.Schemas.CreateMessageSchema.Builder();
    } else {
      return new com.example.UserService.Schemas.CreateMessageSchema.Builder(other);
    }
  }

  /**
   * Creates a new CreateMessageSchema RecordBuilder by copying an existing CreateMessageSchema instance.
   * @param other The existing instance to copy.
   * @return A new CreateMessageSchema RecordBuilder
   */
  public static com.example.UserService.Schemas.CreateMessageSchema.Builder newBuilder(com.example.UserService.Schemas.CreateMessageSchema other) {
    if (other == null) {
      return new com.example.UserService.Schemas.CreateMessageSchema.Builder();
    } else {
      return new com.example.UserService.Schemas.CreateMessageSchema.Builder(other);
    }
  }

  /**
   * RecordBuilder for CreateMessageSchema instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CreateMessageSchema>
    implements org.apache.avro.data.RecordBuilder<CreateMessageSchema> {

    /** User e-mail */
    private java.lang.CharSequence user_email;
    /** Type of message */
    private int message_type;
    /** Delivery method (notification, e-mail, sms...) */
    private int delivery_method;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.UserService.Schemas.CreateMessageSchema.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.user_email)) {
        this.user_email = data().deepCopy(fields()[0].schema(), other.user_email);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.message_type)) {
        this.message_type = data().deepCopy(fields()[1].schema(), other.message_type);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.delivery_method)) {
        this.delivery_method = data().deepCopy(fields()[2].schema(), other.delivery_method);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing CreateMessageSchema instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.UserService.Schemas.CreateMessageSchema other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.user_email)) {
        this.user_email = data().deepCopy(fields()[0].schema(), other.user_email);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.message_type)) {
        this.message_type = data().deepCopy(fields()[1].schema(), other.message_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.delivery_method)) {
        this.delivery_method = data().deepCopy(fields()[2].schema(), other.delivery_method);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'user_email' field.
      * User e-mail
      * @return The value.
      */
    public java.lang.CharSequence getUserEmail() {
      return user_email;
    }


    /**
      * Sets the value of the 'user_email' field.
      * User e-mail
      * @param value The value of 'user_email'.
      * @return This builder.
      */
    public com.example.UserService.Schemas.CreateMessageSchema.Builder setUserEmail(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.user_email = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'user_email' field has been set.
      * User e-mail
      * @return True if the 'user_email' field has been set, false otherwise.
      */
    public boolean hasUserEmail() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'user_email' field.
      * User e-mail
      * @return This builder.
      */
    public com.example.UserService.Schemas.CreateMessageSchema.Builder clearUserEmail() {
      user_email = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'message_type' field.
      * Type of message
      * @return The value.
      */
    public int getMessageType() {
      return message_type;
    }


    /**
      * Sets the value of the 'message_type' field.
      * Type of message
      * @param value The value of 'message_type'.
      * @return This builder.
      */
    public com.example.UserService.Schemas.CreateMessageSchema.Builder setMessageType(int value) {
      validate(fields()[1], value);
      this.message_type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'message_type' field has been set.
      * Type of message
      * @return True if the 'message_type' field has been set, false otherwise.
      */
    public boolean hasMessageType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'message_type' field.
      * Type of message
      * @return This builder.
      */
    public com.example.UserService.Schemas.CreateMessageSchema.Builder clearMessageType() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'delivery_method' field.
      * Delivery method (notification, e-mail, sms...)
      * @return The value.
      */
    public int getDeliveryMethod() {
      return delivery_method;
    }


    /**
      * Sets the value of the 'delivery_method' field.
      * Delivery method (notification, e-mail, sms...)
      * @param value The value of 'delivery_method'.
      * @return This builder.
      */
    public com.example.UserService.Schemas.CreateMessageSchema.Builder setDeliveryMethod(int value) {
      validate(fields()[2], value);
      this.delivery_method = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'delivery_method' field has been set.
      * Delivery method (notification, e-mail, sms...)
      * @return True if the 'delivery_method' field has been set, false otherwise.
      */
    public boolean hasDeliveryMethod() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'delivery_method' field.
      * Delivery method (notification, e-mail, sms...)
      * @return This builder.
      */
    public com.example.UserService.Schemas.CreateMessageSchema.Builder clearDeliveryMethod() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CreateMessageSchema build() {
      try {
        CreateMessageSchema record = new CreateMessageSchema();
        record.user_email = fieldSetFlags()[0] ? this.user_email : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.message_type = fieldSetFlags()[1] ? this.message_type : (java.lang.Integer) defaultValue(fields()[1]);
        record.delivery_method = fieldSetFlags()[2] ? this.delivery_method : (java.lang.Integer) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CreateMessageSchema>
    WRITER$ = (org.apache.avro.io.DatumWriter<CreateMessageSchema>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CreateMessageSchema>
    READER$ = (org.apache.avro.io.DatumReader<CreateMessageSchema>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.user_email);

    out.writeInt(this.message_type);

    out.writeInt(this.delivery_method);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.user_email = in.readString(this.user_email instanceof Utf8 ? (Utf8)this.user_email : null);

      this.message_type = in.readInt();

      this.delivery_method = in.readInt();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.user_email = in.readString(this.user_email instanceof Utf8 ? (Utf8)this.user_email : null);
          break;

        case 1:
          this.message_type = in.readInt();
          break;

        case 2:
          this.delivery_method = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










