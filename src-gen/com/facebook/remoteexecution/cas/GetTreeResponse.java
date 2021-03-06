/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.remoteexecution.cas;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)")
public class GetTreeResponse implements org.apache.thrift.TBase<GetTreeResponse, GetTreeResponse._Fields>, java.io.Serializable, Cloneable, Comparable<GetTreeResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GetTreeResponse");

  private static final org.apache.thrift.protocol.TField DIRECTORIES_FIELD_DESC = new org.apache.thrift.protocol.TField("directories", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField NEXT_PAGE_TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("next_page_token", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new GetTreeResponseStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new GetTreeResponseTupleSchemeFactory();

  public java.util.List<Directory> directories; // required
  public java.lang.String next_page_token; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DIRECTORIES((short)1, "directories"),
    NEXT_PAGE_TOKEN((short)2, "next_page_token");

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
        case 1: // DIRECTORIES
          return DIRECTORIES;
        case 2: // NEXT_PAGE_TOKEN
          return NEXT_PAGE_TOKEN;
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
  private static final _Fields optionals[] = {_Fields.NEXT_PAGE_TOKEN};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DIRECTORIES, new org.apache.thrift.meta_data.FieldMetaData("directories", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Directory.class))));
    tmpMap.put(_Fields.NEXT_PAGE_TOKEN, new org.apache.thrift.meta_data.FieldMetaData("next_page_token", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GetTreeResponse.class, metaDataMap);
  }

  public GetTreeResponse() {
  }

  public GetTreeResponse(
    java.util.List<Directory> directories)
  {
    this();
    this.directories = directories;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetTreeResponse(GetTreeResponse other) {
    if (other.isSetDirectories()) {
      java.util.List<Directory> __this__directories = new java.util.ArrayList<Directory>(other.directories.size());
      for (Directory other_element : other.directories) {
        __this__directories.add(new Directory(other_element));
      }
      this.directories = __this__directories;
    }
    if (other.isSetNext_page_token()) {
      this.next_page_token = other.next_page_token;
    }
  }

  public GetTreeResponse deepCopy() {
    return new GetTreeResponse(this);
  }

  @Override
  public void clear() {
    this.directories = null;
    this.next_page_token = null;
  }

  public int getDirectoriesSize() {
    return (this.directories == null) ? 0 : this.directories.size();
  }

  public java.util.Iterator<Directory> getDirectoriesIterator() {
    return (this.directories == null) ? null : this.directories.iterator();
  }

  public void addToDirectories(Directory elem) {
    if (this.directories == null) {
      this.directories = new java.util.ArrayList<Directory>();
    }
    this.directories.add(elem);
  }

  public java.util.List<Directory> getDirectories() {
    return this.directories;
  }

  public GetTreeResponse setDirectories(java.util.List<Directory> directories) {
    this.directories = directories;
    return this;
  }

  public void unsetDirectories() {
    this.directories = null;
  }

  /** Returns true if field directories is set (has been assigned a value) and false otherwise */
  public boolean isSetDirectories() {
    return this.directories != null;
  }

  public void setDirectoriesIsSet(boolean value) {
    if (!value) {
      this.directories = null;
    }
  }

  public java.lang.String getNext_page_token() {
    return this.next_page_token;
  }

  public GetTreeResponse setNext_page_token(java.lang.String next_page_token) {
    this.next_page_token = next_page_token;
    return this;
  }

  public void unsetNext_page_token() {
    this.next_page_token = null;
  }

  /** Returns true if field next_page_token is set (has been assigned a value) and false otherwise */
  public boolean isSetNext_page_token() {
    return this.next_page_token != null;
  }

  public void setNext_page_tokenIsSet(boolean value) {
    if (!value) {
      this.next_page_token = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case DIRECTORIES:
      if (value == null) {
        unsetDirectories();
      } else {
        setDirectories((java.util.List<Directory>)value);
      }
      break;

    case NEXT_PAGE_TOKEN:
      if (value == null) {
        unsetNext_page_token();
      } else {
        setNext_page_token((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case DIRECTORIES:
      return getDirectories();

    case NEXT_PAGE_TOKEN:
      return getNext_page_token();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case DIRECTORIES:
      return isSetDirectories();
    case NEXT_PAGE_TOKEN:
      return isSetNext_page_token();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof GetTreeResponse)
      return this.equals((GetTreeResponse)that);
    return false;
  }

  public boolean equals(GetTreeResponse that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_directories = true && this.isSetDirectories();
    boolean that_present_directories = true && that.isSetDirectories();
    if (this_present_directories || that_present_directories) {
      if (!(this_present_directories && that_present_directories))
        return false;
      if (!this.directories.equals(that.directories))
        return false;
    }

    boolean this_present_next_page_token = true && this.isSetNext_page_token();
    boolean that_present_next_page_token = true && that.isSetNext_page_token();
    if (this_present_next_page_token || that_present_next_page_token) {
      if (!(this_present_next_page_token && that_present_next_page_token))
        return false;
      if (!this.next_page_token.equals(that.next_page_token))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetDirectories()) ? 131071 : 524287);
    if (isSetDirectories())
      hashCode = hashCode * 8191 + directories.hashCode();

    hashCode = hashCode * 8191 + ((isSetNext_page_token()) ? 131071 : 524287);
    if (isSetNext_page_token())
      hashCode = hashCode * 8191 + next_page_token.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(GetTreeResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetDirectories()).compareTo(other.isSetDirectories());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDirectories()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.directories, other.directories);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetNext_page_token()).compareTo(other.isSetNext_page_token());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNext_page_token()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.next_page_token, other.next_page_token);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("GetTreeResponse(");
    boolean first = true;

    sb.append("directories:");
    if (this.directories == null) {
      sb.append("null");
    } else {
      sb.append(this.directories);
    }
    first = false;
    if (isSetNext_page_token()) {
      if (!first) sb.append(", ");
      sb.append("next_page_token:");
      if (this.next_page_token == null) {
        sb.append("null");
      } else {
        sb.append(this.next_page_token);
      }
      first = false;
    }
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

  private static class GetTreeResponseStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GetTreeResponseStandardScheme getScheme() {
      return new GetTreeResponseStandardScheme();
    }
  }

  private static class GetTreeResponseStandardScheme extends org.apache.thrift.scheme.StandardScheme<GetTreeResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetTreeResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DIRECTORIES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list80 = iprot.readListBegin();
                struct.directories = new java.util.ArrayList<Directory>(_list80.size);
                Directory _elem81;
                for (int _i82 = 0; _i82 < _list80.size; ++_i82)
                {
                  _elem81 = new Directory();
                  _elem81.read(iprot);
                  struct.directories.add(_elem81);
                }
                iprot.readListEnd();
              }
              struct.setDirectoriesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NEXT_PAGE_TOKEN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.next_page_token = iprot.readString();
              struct.setNext_page_tokenIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetTreeResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.directories != null) {
        oprot.writeFieldBegin(DIRECTORIES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.directories.size()));
          for (Directory _iter83 : struct.directories)
          {
            _iter83.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.next_page_token != null) {
        if (struct.isSetNext_page_token()) {
          oprot.writeFieldBegin(NEXT_PAGE_TOKEN_FIELD_DESC);
          oprot.writeString(struct.next_page_token);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GetTreeResponseTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GetTreeResponseTupleScheme getScheme() {
      return new GetTreeResponseTupleScheme();
    }
  }

  private static class GetTreeResponseTupleScheme extends org.apache.thrift.scheme.TupleScheme<GetTreeResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GetTreeResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDirectories()) {
        optionals.set(0);
      }
      if (struct.isSetNext_page_token()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetDirectories()) {
        {
          oprot.writeI32(struct.directories.size());
          for (Directory _iter84 : struct.directories)
          {
            _iter84.write(oprot);
          }
        }
      }
      if (struct.isSetNext_page_token()) {
        oprot.writeString(struct.next_page_token);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GetTreeResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list85 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.directories = new java.util.ArrayList<Directory>(_list85.size);
          Directory _elem86;
          for (int _i87 = 0; _i87 < _list85.size; ++_i87)
          {
            _elem86 = new Directory();
            _elem86.read(iprot);
            struct.directories.add(_elem86);
          }
        }
        struct.setDirectoriesIsSet(true);
      }
      if (incoming.get(1)) {
        struct.next_page_token = iprot.readString();
        struct.setNext_page_tokenIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

