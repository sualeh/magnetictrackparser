/*
 *
 * Magnetic Track Parser
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
import us.fatehi.magnetictrack.BaseTrackData;

public class Name
  extends BaseTrackData
{

  private static final long serialVersionUID = 5843389621643018055L;

  private final String firstName;
  private final String lastName;

  public Name()
  {
    this(null);
  }

  public Name(final String rawName)
  {
    super(rawName);

    final String[] splitName = trimToEmpty(rawName).split("/");
    firstName = capitalize(splitName[splitName.length - 1]);
    if (splitName.length > 1)
    {
      lastName = capitalize(splitName[0]);
    }
    else
    {
      lastName = "";
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
    if (firstName == null)
    {
      if (other.firstName != null)
      {
        return false;
      }
    }
    else if (!firstName.equals(other.firstName))
    {
      return false;
    }
    if (lastName == null)
    {
      if (other.lastName != null)
      {
        return false;
      }
    }
    else if (!lastName.equals(other.lastName))
    {
      return false;
    }
    return true;
  }

  @Override
  public boolean exceedsMaximumLength()
  {
    return trimToEmpty(getRawTrackData()).length() > 26;
  }

  /**
   * @return the firstName
   */
  public String getFirstName()
  {
    return firstName;
  }

  public String getFullName()
  {
    final StringBuilder buffer = new StringBuilder();
    buffer.append(trimToEmpty(firstName));
    if (!isBlank(lastName))
    {
      buffer.append(" ").append(trimToEmpty(lastName));
    }
    return buffer.toString();
  }

  /**
   * @return the lastName
   */
  public String getLastName()
  {
    return lastName;
  }

  public boolean hasFirstName()
  {
    return !isBlank(firstName);
  }

  public boolean hasFullName()
  {
    return hasFirstName() && hasLastName();
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (firstName == null? 0: firstName.hashCode());
    result = prime * result + (lastName == null? 0: lastName.hashCode());
    return result;
  }

  public boolean hasLastName()
  {
    return !isBlank(lastName);
  }

  public boolean hasName()
  {
    return hasFirstName() || hasLastName();
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return getFullName();
  }

  private String capitalize(final String splitName)
  {
    return capitalizeFully(trimToEmpty(splitName), new char[] {
        '.', '\'', ' '
    });
  }

}
