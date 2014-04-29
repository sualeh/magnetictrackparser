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
package us.fatehi.magnetictrack;


import java.io.Serializable;

public interface TrackData
  extends Serializable
{

  /**
   * Whether the track data exceeds the maximum length allowed.
   *
   * @return True if too long
   */
  boolean exceedsMaximumLength();

  /**
   * Raw track data.
   *
   * @return Raw track data
   */
  String getRawTrackData();

  /**
   * Whether raw track data is present.
   *
   * @return True is raw track data is available
   */
  boolean hasRawTrackData();

}
