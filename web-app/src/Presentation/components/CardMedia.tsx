import { useEffect, useState } from "react";
import brokenImage from '../assets/broken-image.png';
import styled from 'styled-components';
import Skeleton from '@mui/material/Skeleton';

interface props {
  src: string,
  alt: string | undefined,
  hovered: boolean
}

export default function CardMedia({ src, alt, hovered }: props) {

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    setLoading(true);
    loadImage();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [src]);

  function loadImage() {
    const img = new Image();

    img.onload = () => setLoading(false);
    img.onerror = () => setError(true);
    
    img.src = src || "";
  }

  if (error)
    return <MediaImage
      src={brokenImage}
      alt="the link of image is broken"
      style={{ filter: "invert(1)" }}
      loading="lazy"
      $hovered={hovered}
    />

  else if (loading)
    return (
      <Skeleton animation="wave" variant="rectangular" width="100%" height={hovered?90:150} />
    );

  else
    return <MediaImage
      src={src}
      alt={alt}
      loading="lazy"
      $hovered={hovered}
    />
}

const MediaImage = styled.img<{$hovered: boolean}>`
  width: 100%;
  height: auto;
  aspect-ratio: ${props => props.$hovered ? "16/9" : "auto"};
  object-fit: cover;
  border-radius: ${props => props.$hovered ? "0" : "4px"};
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
`;