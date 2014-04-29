/*
 *
 * Magnetic Stripe Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package us.fatehi.magnetictrack.bankcard;


import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

import java.io.Serializable;

public class Name
  implements Serializable
{

  private static final long serialVersionUID = 5843389621643018055L;

  private final String name;
  private final String firstName;
  private final String lastName;

  public Name()
  {
    this("");
  }

  public Name(final String name)
  {
    this.name = name;
    if (isBlank(name))
    {
      firstName = "";
      lastName = "";
    }
    else
    {
      final String[] splitName = name.split("/");
      firstName = capitalizeFully(trimToEmpty(splitName[splitName.length - 1]),
                                  new char[] {
                                      '.', ' '
                                  });
      if (splitName.length > 1)
      {
        lastName = capitalizeFully(trimToEmpty(splitName[0]), new char[] {
          '.'
        });
      }
      else
      {
        lastName = "";
      }
    }
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (!(obj instanceof Name))
    {
      return false;
    }
    final Name other = (Name) obj;
    if (name == null)
    {
      if (other.name != null)
      {
        return false;
      }
    }
    else if (!name.equals(other.name))
    {
      return false;
    }
    return true;
  }

  /**
   * @return the firstName
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (name == null? 0: name.hashCode());
    return result;
  }

  public boolean hasName()
  {
    return !(isBlank(firstName) && isBlank(lastName));
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return getFullName();
  }

  private String getFullName()
  {
    final StringBuilder buffer = new StringBuilder();
    buffer.append(trimToEmpty(firstName));
    if (!isBlank(lastName))
    {
      buffer.append(" ").append(trimToEmpty(lastName));
    }
    return buffer.toString();
  }

}
