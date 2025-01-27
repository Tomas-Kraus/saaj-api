/*
 * Copyright (c) 2004, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.soap;

import java.util.Iterator;

import javax.xml.namespace.QName;

/**
 * An object representing an element of a SOAP message that is allowed but not
 * specifically prescribed by a SOAP specification. This interface serves as the
 * base interface for those objects that are specifically prescribed by a SOAP
 * specification.
 * <p>
 * Methods in this interface that are required to return SAAJ specific objects
 * may "silently" replace nodes in the tree as required to successfully return
 * objects of the correct type. See {@link #getChildElements()} and
 * {@link jakarta.xml.soap} for details.
 *
 * @since 1.6
 */
public interface SOAPElement extends Node, org.w3c.dom.Element {

    /**
     * Creates a new {@code SOAPElement} object initialized with the
     * given {@code Name} object and adds the new element to this
     * {@code SOAPElement} object.
     * <P>
     * This method may be deprecated in a future release of SAAJ in favor of
     * addChildElement(javax.xml.namespace.QName)
     *
     * @param name a {@code Name} object with the XML name for the
     *        new element
     *
     * @return the new {@code SOAPElement} object that was created
     * @exception SOAPException if there is an error in creating the
     *                          {@code SOAPElement} object
     * @see SOAPElement#addChildElement(javax.xml.namespace.QName)
     */
    public SOAPElement addChildElement(Name name) throws SOAPException;

    /**
     * Creates a new {@code SOAPElement} object initialized with the given
     * {@code QName} object and adds the new element to this {@code SOAPElement}
     *  object. The  <i>namespace</i>, <i>localname</i> and <i>prefix</i> of the new
     * {@code SOAPElement} are all taken  from the {@code qname} argument.
     *
     * @param qname a {@code QName} object with the XML name for the
     *        new element
     *
     * @return the new {@code SOAPElement} object that was created
     * @exception SOAPException if there is an error in creating the
     *                          {@code SOAPElement} object
     * @see SOAPElement#addChildElement(Name)
     * @since 1.6, SAAJ 1.3
     */
    public SOAPElement addChildElement(QName qname) throws SOAPException;

    /**
     * Creates a new {@code SOAPElement} object initialized with the
     * specified local name and adds the new element to this
     * {@code SOAPElement} object.
     * The new  {@code SOAPElement} inherits any in-scope default namespace.
     *
     * @param localName a {@code String} giving the local name for
     *          the element
     * @return the new {@code SOAPElement} object that was created
     * @exception SOAPException if there is an error in creating the
     *                          {@code SOAPElement} object
     */
    public SOAPElement addChildElement(String localName) throws SOAPException;

    /**
     * Creates a new {@code SOAPElement} object initialized with the
     * specified local name and prefix and adds the new element to this
     * {@code SOAPElement} object.
     *
     * @param localName a {@code String} giving the local name for
     *        the new element
     * @param prefix a {@code String} giving the namespace prefix for
     *        the new element
     *
     * @return the new {@code SOAPElement} object that was created
     * @exception SOAPException if the {@code prefix} is not valid in the
     *         context of this {@code SOAPElement} or  if there is an error in creating the
     *                          {@code SOAPElement} object
     */
    public SOAPElement addChildElement(String localName, String prefix)
        throws SOAPException;

    /**
     * Creates a new {@code SOAPElement} object initialized with the
     * specified local name, prefix, and URI and adds the new element to this
     * {@code SOAPElement} object.
     *
     * @param localName a {@code String} giving the local name for
     *        the new element
     * @param prefix a {@code String} giving the namespace prefix for
     *        the new element
     * @param uri a {@code String} giving the URI of the namespace
     *        to which the new element belongs
     *
     * @return the new {@code SOAPElement} object that was created
     * @exception SOAPException if there is an error in creating the
     *                          {@code SOAPElement} object
     */
    public SOAPElement addChildElement(String localName, String prefix,
                                       String uri)
        throws SOAPException;

    /**
     * Add a {@code SOAPElement} as a child of this
     * {@code SOAPElement} instance. The {@code SOAPElement}
     * is expected to be created by a
     * {@code SOAPFactory}. Callers should not rely on the
     * element instance being added as is into the XML
     * tree. Implementations could end up copying the content
     * of the {@code SOAPElement} passed into an instance of
     * a different {@code SOAPElement} implementation. For
     * instance if {@code addChildElement()} is called on a
     * {@code SOAPHeader}, {@code element} will be copied
     * into an instance of a {@code SOAPHeaderElement}.
     *
     * <P>The fragment rooted in {@code element} is either added
     * as a whole or not at all, if there was an error.
     *
     * <P>The fragment rooted in {@code element} cannot contain
     * elements named "Envelope", "Header" or "Body" and in the SOAP
     * namespace. Any namespace prefixes present in the fragment
     * should be fully resolved using appropriate namespace
     * declarations within the fragment itself.
     *
     * @param element the {@code SOAPElement} to be added as a
     *                new child
     *
     * @exception SOAPException if there was an error in adding this
     *                          element as a child
     *
     * @return an instance representing the new SOAP element that was
     *         actually added to the tree.
     */
    public SOAPElement addChildElement(SOAPElement element)
        throws SOAPException;

    /**
     * Detaches all children of this {@code SOAPElement}.
     * <p>
     * This method is useful for rolling back the construction of partially
     * completed {@code SOAPHeaders} and {@code SOAPBodys} in
     * preparation for sending a fault when an error condition is detected. It
     * is also useful for recycling portions of a document within a SOAP
     * message.
     *
     * @since 1.6, SAAJ 1.2
     */
    public abstract void removeContents();

    /**
     * Creates a new {@code Text} object initialized with the given
     * {@code String} and adds it to this {@code SOAPElement} object.
     *
     * @param text a {@code String} object with the textual content to be added
     *
     * @return the {@code SOAPElement} object into which
     *         the new {@code Text} object was inserted
     * @exception SOAPException if there is an error in creating the
     *                    new {@code Text} object or if it is not legal to
     *                      attach it as a child to this
     *                      {@code SOAPElement}
     */
    public SOAPElement addTextNode(String text) throws SOAPException;

    /**
     * Adds an attribute with the specified name and value to this
     * {@code SOAPElement} object.
     *
     * @param name a {@code Name} object with the name of the attribute
     * @param value a {@code String} giving the value of the attribute
     * @return the {@code SOAPElement} object into which the attribute was
     *         inserted
     *
     * @exception SOAPException if there is an error in creating the
     *                          Attribute, or it is invalid to set
                                an attribute with {@code Name}
                                 {@code name} on this SOAPElement.
     * @see SOAPElement#addAttribute(javax.xml.namespace.QName, String)
     */
    public SOAPElement addAttribute(Name name, String value)
        throws SOAPException;

    /**
     * Adds an attribute with the specified name and value to this
     * {@code SOAPElement} object.
     *
     * @param qname a {@code QName} object with the name of the attribute
     * @param value a {@code String} giving the value of the attribute
     * @return the {@code SOAPElement} object into which the attribute was
     *         inserted
     *
     * @exception SOAPException if there is an error in creating the
     *                          Attribute, or it is invalid to set
                                an attribute with {@code QName}
                                {@code qname} on this SOAPElement.
     * @see SOAPElement#addAttribute(Name, String)
     * @since 1.6, SAAJ 1.3
     */
    public SOAPElement addAttribute(QName qname, String value)
        throws SOAPException;

    /**
     * Adds a namespace declaration with the specified prefix and URI to this
     * {@code SOAPElement} object.
     *
     * @param prefix a {@code String} giving the prefix of the namespace
     * @param uri a {@code String} giving the uri of the namespace
     * @return the {@code SOAPElement} object into which this
     *          namespace declaration was inserted.
     *
     * @exception SOAPException if there is an error in creating the
     *                          namespace
     */
    public SOAPElement addNamespaceDeclaration(String prefix, String uri)
        throws SOAPException;

    /**
     * Returns the value of the attribute with the specified name.
     *
     * @param name a {@code Name} object with the name of the attribute
     * @return a {@code String} giving the value of the specified
     *         attribute, Null if there is no such attribute
     * @see SOAPElement#getAttributeValue(javax.xml.namespace.QName)
     */
    public String getAttributeValue(Name name);

    /**
     * Returns the value of the attribute with the specified qname.
     *
     * @param qname a {@code QName} object with the qname of the attribute
     * @return a {@code String} giving the value of the specified
     *         attribute, Null if there is no such attribute
     * @see SOAPElement#getAttributeValue(Name)
     * @since 1.6, SAAJ 1.3
     */
    public String getAttributeValue(QName qname);

    /**
     * Returns an {@code Iterator} over all of the attribute
     * {@code Name} objects in this
     * {@code SOAPElement} object. The iterator can be used to get
     * the attribute names, which can then be passed to the method
     * {@code getAttributeValue} to retrieve the value of each
     * attribute.
     *
     * @see SOAPElement#getAllAttributesAsQNames()
     * @return an iterator over the names of the attributes
     */
    public Iterator<Name> getAllAttributes();

    /**
     * Returns an {@code Iterator} over all of the attributes
     * in this {@code SOAPElement}  as {@code QName} objects.
     * The iterator can be used to get the attribute QName, which can then
     * be passed to the method {@code getAttributeValue} to retrieve
     * the value of each attribute.
     *
     * @return an iterator over the QNames of the attributes
     * @see SOAPElement#getAllAttributes()
     * @since 1.6, SAAJ 1.3
     */
    public Iterator<QName> getAllAttributesAsQNames();


    /**
     * Returns the URI of the namespace that has the given prefix.
     *
     * @param prefix a {@code String} giving the prefix of the namespace
     *        for which to search
     * @return a {@code String} with the uri of the namespace that has
     *        the given prefix
     */
    public String getNamespaceURI(String prefix);

    /**
     * Returns an {@code Iterator} over the namespace prefix
     * {@code String}s declared by this element. The prefixes returned by
     * this iterator can be passed to the method
     * {@code getNamespaceURI} to retrieve the URI of each namespace.
     *
     * @return an iterator over the namespace prefixes in this
     *         {@code SOAPElement} object
     */
    public Iterator<String> getNamespacePrefixes();

    /**
     * Returns an {@code Iterator} over the namespace prefix
     * {@code String}s visible to this element. The prefixes returned by
     * this iterator can be passed to the method
     * {@code getNamespaceURI} to retrieve the URI of each namespace.
     *
     * @return an iterator over the namespace prefixes are within scope of this
     *         {@code SOAPElement} object
     *
     * @since 1.6, SAAJ 1.2
     */
    public Iterator<String> getVisibleNamespacePrefixes();

    /**
     * Creates a {@code QName} whose namespace URI is the one associated
     * with the parameter, {@code prefix}, in the context of this
     * {@code SOAPElement}. The remaining elements of the new
     * {@code QName} are taken directly from the parameters,
     * {@code localName} and {@code prefix}.
     *
     * @param localName
     *          a {@code String} containing the local part of the name.
     * @param prefix
     *          a {@code String} containing the prefix for the name.
     *
     * @return a {@code QName} with the specified {@code localName}
     *          and {@code prefix}, and with a namespace that is associated
     *          with the {@code prefix} in the context of this
     *          {@code SOAPElement}. This namespace will be the same as
     *          the one that would be returned by
     *          {@link #getNamespaceURI(String)} if it were given
     *          {@code prefix} as it's parameter.
     *
     * @exception SOAPException if the {@code QName} cannot be created.
     *
     * @since 1.6, SAAJ 1.3
     */
    public QName createQName(String localName, String prefix)
        throws SOAPException;
    /**
     * Returns the name of this {@code SOAPElement} object.
     *
     * @return a {@code Name} object with the name of this
     *         {@code SOAPElement} object
     */
    public Name getElementName();

    /**
     * Returns the qname of this {@code SOAPElement} object.
     *
     * @return a {@code QName} object with the qname of this
     *         {@code SOAPElement} object
     * @see SOAPElement#getElementName()
     * @since 1.6, SAAJ 1.3
     */
    public QName getElementQName();

    /**
    * Changes the name of this {@code Element} to {@code newName} if
    * possible. SOAP Defined elements such as SOAPEnvelope, SOAPHeader, SOAPBody
    * etc. cannot have their names changed using this method. Any attempt to do
    * so will result in a  SOAPException being thrown.
    *<P>
    * Callers should not rely on the element instance being renamed as is.
    * Implementations could end up copying the content of the
    * {@code SOAPElement} to a renamed instance.
    *
    * @param newName the new name for the {@code Element}.
    *
    * @exception SOAPException if changing the name of this {@code Element}
    *                          is not allowed.
    * @return The renamed Node
    *
    * @since 1.6, SAAJ 1.3
    */
   public SOAPElement setElementQName(QName newName) throws SOAPException;

   /**
     * Removes the attribute with the specified name.
     *
     * @param name the {@code Name} object with the name of the
     *        attribute to be removed
     * @return {@code true} if the attribute was
     *         removed successfully; {@code false} if it was not
     * @see SOAPElement#removeAttribute(javax.xml.namespace.QName)
     */
    public boolean removeAttribute(Name name);

    /**
     * Removes the attribute with the specified qname.
     *
     * @param qname the {@code QName} object with the qname of the
     *        attribute to be removed
     * @return {@code true} if the attribute was
     *         removed successfully; {@code false} if it was not
     * @see SOAPElement#removeAttribute(Name)
     * @since 1.6, SAAJ 1.3
     */
    public boolean removeAttribute(QName qname);

    /**
     * Removes the namespace declaration corresponding to the given prefix.
     *
     * @param prefix a {@code String} giving the prefix for which
     *        to search
     * @return {@code true} if the namespace declaration was
     *         removed successfully; {@code false} if it was not
     */
    public boolean removeNamespaceDeclaration(String prefix);

    /**
     * Returns an {@code Iterator} over all the immediate child
     * {@link Node}s of this element. This includes {@code jakarta.xml.soap.Text}
     * objects as well as {@code SOAPElement} objects.
     * <p>
     * Calling this method must cause child {@code Element},
     * {@code SOAPElement} and {@code org.w3c.dom.Text} nodes to be
     * replaced by {@code SOAPElement}, {@code SOAPHeaderElement},
     * {@code SOAPBodyElement} or {@code jakarta.xml.soap.Text} nodes as
     * appropriate for the type of this parent node. As a result the calling
     * application must treat any existing references to these child nodes that
     * have been obtained through DOM APIs as invalid and either discard them or
     * refresh them with the values returned by this {@code Iterator}. This
     * behavior can be avoided by calling the equivalent DOM APIs. See
     * {@link jakarta.xml.soap}
     * for more details.
     *
     * @return an iterator with the content of this {@code SOAPElement}
     *         object
     */
    public Iterator<Node> getChildElements();

    /**
     * Returns an {@code Iterator} over all the immediate child
     * {@link Node}s of this element with the specified name. All of these
     * children will be {@code SOAPElement} nodes.
     * <p>
     * Calling this method must cause child {@code Element},
     * {@code SOAPElement} and {@code org.w3c.dom.Text} nodes to be
     * replaced by {@code SOAPElement}, {@code SOAPHeaderElement},
     * {@code SOAPBodyElement} or {@code jakarta.xml.soap.Text} nodes as
     * appropriate for the type of this parent node. As a result the calling
     * application must treat any existing references to these child nodes that
     * have been obtained through DOM APIs as invalid and either discard them or
     * refresh them with the values returned by this {@code Iterator}. This
     * behavior can be avoided by calling the equivalent DOM APIs. See
     * {@link jakarta.xml.soap}
     * for more details.
     *
     * @param name a {@code Name} object with the name of the child
     *        elements to be returned
     *
     * @return an {@code Iterator} object over all the elements
     *         in this {@code SOAPElement} object with the
     *         specified name
     * @see SOAPElement#getChildElements(javax.xml.namespace.QName)
     */
    public Iterator<Node> getChildElements(Name name);

    /**
     * Returns an {@code Iterator} over all the immediate child
     * {@link Node}s of this element with the specified qname. All of these
     * children will be {@code SOAPElement} nodes.
     * <p>
     * Calling this method must cause child {@code Element},
     * {@code SOAPElement} and {@code org.w3c.dom.Text} nodes to be
     * replaced by {@code SOAPElement}, {@code SOAPHeaderElement},
     * {@code SOAPBodyElement} or {@code jakarta.xml.soap.Text} nodes as
     * appropriate for the type of this parent node. As a result the calling
     * application must treat any existing references to these child nodes that
     * have been obtained through DOM APIs as invalid and either discard them or
     * refresh them with the values returned by this {@code Iterator}. This
     * behavior can be avoided by calling the equivalent DOM APIs. See
     * {@link jakarta.xml.soap}
     * for more details.
     *
     * @param qname a {@code QName} object with the qname of the child
     *        elements to be returned
     *
     * @return an {@code Iterator} object over all the elements
     *         in this {@code SOAPElement} object with the
     *         specified qname
     * @see SOAPElement#getChildElements(Name)
     * @since 1.6, SAAJ 1.3
     */
    public Iterator<Node> getChildElements(QName qname);

    /**
     * Sets the encoding style for this {@code SOAPElement} object
     * to one specified.
     *
     * @param encodingStyle a {@code String} giving the encoding style
     *
     * @exception IllegalArgumentException if there was a problem in the
     *            encoding style being set.
     * @exception SOAPException if setting the encodingStyle is invalid for this SOAPElement.
     * @see #getEncodingStyle
     */
    public void setEncodingStyle(String encodingStyle)
        throws SOAPException;
    /**
     * Returns the encoding style for this {@code SOAPElement} object.
     *
     * @return a {@code String} giving the encoding style
     *
     * @see #setEncodingStyle
     */
    public String getEncodingStyle();
}
