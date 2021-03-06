/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.remoteexecution.executionengine;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)")
public class CancelExecutionRequest implements org.apache.thrift.TBase<CancelExecutionRequest, CancelExecutionRequest._Fields>, java.io.Serializable, Cloneable, Comparable<CancelExecutionRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CancelExecutionRequest");

  private static final org.apache.thrift.protocol.TField EXECUTION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("execution_id", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new CancelExecutionRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new CancelExecutionRequestTupleSchemeFactory();

  public java.lang.String execution_id; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EXECUTION_ID((short)1, "execution_id");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // EXECUTION_ID
          return EXECUTION_ID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EXECUTION_ID, new org.apache.thrift.meta_data.FieldMetaData("execution_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CancelExecutionRequest.class, metaDataMap);
  }

  public CancelExecutionRequest() {
  }

  public CancelExecutionRequest(
    java.lang.String execution_id)
  {
    this();
    this.execution_id = execution_id;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CancelExecutionRequest(CancelExecutionRequest other) {
    if (other.isSetExecution_id()) {
      this.execution_id = other.execution_id;
    }
  }

  public CancelExecutionRequest deepCopy() {
    return new CancelExecutionRequest(this);
  }

  @Override
  public void clear() {
    this.execution_id = null;
  }

  public java.lang.String getExecution_id() {
    return this.execution_id;
  }

  public CancelExecutionRequest setExecution_id(java.lang.String execution_id) {
    this.execution_id = execution_id;
    return this;
  }

  public void unsetExecution_id() {
    this.execution_id = null;
  }

  /** Returns true if field execution_id is set (has been assigned a value) and false otherwise */
  public boolean isSetExecution_id() {
    return this.execution_id != null;
  }

  public void setExecution_idIsSet(boolean value) {
    if (!value) {
      this.execution_id = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case EXECUTION_ID:
      if (value == null) {
        unsetExecution_id();
      } else {
        setExecution_id((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case EXECUTION_ID:
      return getExecution_id();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case EXECUTION_ID:
      return isSetExecution_id();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof CancelExecutionRequest)
      return this.equals((CancelExecutionRequest)that);
    return false;
  }

  public boolean equals(CancelExecutionRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_execution_id = true && this.isSetExecution_id();
    boolean that_present_execution_id = true && that.isSetExecution_id();
    if (this_present_execution_id || that_present_execution_id) {
      if (!(this_present_execution_id && that_present_execution_id))
        return false;
      if (!this.execution_id.equals(that.execution_id))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetExecution_id()) ? 131071 : 524287);
    if (isSetExecution_id())
      hashCode = hashCode * 8191 + execution_id.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(CancelExecutionRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetExecution_id()).compareTo(other.isSetExecution_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExecution_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.execution_id, other.execution_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("CancelExecutionRequest(");
    boolean first = true;

    sb.append("execution_id:");
    if (this.execution_id == null) {
      sb.append("null");
    } else {
      sb.append(this.execution_id);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CancelExecutionRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public CancelExecutionRequestStandardScheme getScheme() {
      return new CancelExecutionRequestStandardScheme();
    }
  }

  private static class CancelExecutionRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<CancelExecutionRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CancelExecutionRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EXECUTION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.execution_id = iprot.readString();
              struct.setExecution_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, CancelExecutionRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.execution_id != null) {
        oprot.writeFieldBegin(EXECUTION_ID_FIELD_DESC);
        oprot.writeString(struct.execution_id);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CancelExecutionRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public CancelExecutionRequestTupleScheme getScheme() {
      return new CancelExecutionRequestTupleScheme();
    }
  }

  private static class CancelExecutionRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<CancelExecutionRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CancelExecutionRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetExecution_id()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetExecution_id()) {
        oprot.writeString(struct.execution_id);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CancelExecutionRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.execution_id = iprot.readString();
        struct.setExecution_idIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

